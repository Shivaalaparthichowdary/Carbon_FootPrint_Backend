package com.example.demo.repository;

import com.example.demo.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // Basic CRUD is enough for now
}