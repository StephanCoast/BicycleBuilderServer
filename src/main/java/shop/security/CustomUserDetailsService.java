package shop.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import shop.model.User;
import shop.repository.UserRepository;

import static java.util.Collections.emptyList;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = userRepository.findByName(name);
        if (user == null) {
            throw new UsernameNotFoundException(name);
        }
        return new org.springframework.security.core.userdetails.User(user.getName(), user.getPasswordHash(), emptyList());
    }
}