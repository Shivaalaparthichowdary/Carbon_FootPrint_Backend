package com.example.demo.service;

import com.example.demo.Entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    // 1. Save a new user
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    // 2. Find user by email (Fixed: unwraps the Optional)
    public User findByEmail(String email) {
        // If user is found, return it. If not, return null.
        return userRepository.findByEmail(email).orElse(null);
    }

    // 3. Load user for Spring Security (Fixed: throws error if missing)
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // If user is found, return it. If not, throw Exception.
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        return new org.springframework.security.core.userdetails.User(
            user.getEmail(), 
            user.getPassword(), 
            new ArrayList<>()
        );
    }
}