package pf.bbserver.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pf.bbserver.model.Benutzer;
import pf.bbserver.repository.BenutzerRepo;

import static java.util.Collections.emptyList;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    BenutzerRepo benutzerRepo;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Benutzer benutzer = benutzerRepo.findByName(name);
        if (benutzer == null) {
            throw new UsernameNotFoundException(name);
        }
        return new org.springframework.security.core.userdetails.User(benutzer.getName(), benutzer.getPasswortHash(), emptyList());
    }
}