package pf.bbserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pf.bbserver.model.User;
import pf.bbserver.repository.UserRepo;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/benutzer")
public class UserController {

    @Autowired
    UserRepo userRepo;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    // registers a new user
    @PostMapping
    public void register(@RequestBody User user) {
        user.setPasswortHash(bCryptPasswordEncoder.encode(user.getPasswortHash()));
        userRepo.save(user);
    }

    // provides only the user names
    @GetMapping
    public List<String> getUserNames() {
        Stream<User> users = StreamSupport.stream(userRepo.findAll().spliterator(), false);
        return users.map(User::getName).sorted().collect(Collectors.toList());
    }

    // provides user details for the registered user
    @GetMapping(value = "/{name}")
    public ResponseEntity getUser(@PathVariable("name") String name) {

        String authenticatedUser = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!name.equals(authenticatedUser)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        User user = userRepo.findByName(name);
        if (user != null) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();
    }
}