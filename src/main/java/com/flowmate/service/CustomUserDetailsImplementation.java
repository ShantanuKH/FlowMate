package com.flowmate.service;

import com.flowmate.model.User;
import com.flowmate.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

// We are writing business logic here, so we annotate it with @Service
@Service
public class CustomUserDetailsImplementation implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Check whether user is available in our database with the given username
        User user = userRepository.findByEmail(username);

        if (user == null) {
            throw new UsernameNotFoundException("User Not Found with the email: " + username);
        }

        // Create a list of granted authorities (you can populate it based on user roles)
        List<GrantedAuthority> authorities = new ArrayList<>();

        // Return Spring Security's User object with email, password, and authorities
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                authorities
        );
    }
}
