package com.example.demo.repository;

import com.example.demo.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    // We will need this later for Login (Authentication)
    Optional<User> findByEmail(String email);
}
