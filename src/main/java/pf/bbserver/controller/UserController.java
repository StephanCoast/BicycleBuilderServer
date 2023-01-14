package pf.bbserver.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import pf.bbserver.model.User;
import pf.bbserver.repository.UserRepo;

import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/users")
public class UserController {

    final
    UserRepo userRepo;

    final
    BCryptPasswordEncoder bCryptPasswordEncoder;
    final static String baseURL= "http://localhost:8080/users/";

    public UserController(BCryptPasswordEncoder bCryptPasswordEncoder, UserRepo userRepo) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepo = userRepo;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User newUser) {

        try {
            // CHECK IF USER IS AN ADMIN USER
            String authUserName = SecurityContextHolder.getContext().getAuthentication().getName();
            User tempUser = userRepo.findByName(authUserName);
            if(!Objects.equals(tempUser.getRole(), "ADMIN")) {
                System.out.println("User has no admin rights to create users!");
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }

            newUser.setPasswordHash(bCryptPasswordEncoder.encode(newUser.getPasswordHash()));
            newUser = userRepo.save(newUser);

            //Return URI somehow necessary for succesful completion of Post task
            URI location = URI.create(baseURL + newUser.getId());
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.setLocation(location);

            return new ResponseEntity<>(newUser, responseHeaders, HttpStatus.CREATED);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") int id, @RequestBody User user) {

        Optional<User> userData = userRepo.findById(id);
        if (userData.isPresent()) {
            if(user.getPasswordHash() == null) {
                // Password Hash muss unverändert bleiben, wenn Password nicht geändert wurde.
                User dbUser = userData.get();
                user.setPasswordHash(dbUser.getPasswordHash());
            } else {
                // Passwort verschlüsseln nach einmalig unverschlüsselter Übertragung
                user.setPasswordHash(bCryptPasswordEncoder.encode(user.getPasswordHash()));
            }

            userRepo.save(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
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