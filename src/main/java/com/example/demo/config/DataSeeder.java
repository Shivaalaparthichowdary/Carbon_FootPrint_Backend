package com.example.demo.config;

import com.example.demo.Entity.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataSeeder implements CommandLineRunner {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {
        // 1. Clear old data so we don't have duplicates or broken links
        productRepository.deleteAll(); 

        // 2. Add Products with LOCAL image paths (stored in frontend/public/images)
        Product p1 = new Product(
            "Bamboo Toothbrush", 
            "100% Biodegradable handle. Plastic-free.", 
            5.99, 
            "/images/Bamboo Toothbrush.jpg"
        );
        
        Product p2 = new Product(
            "Solar Power Bank", 
            "Charge your phone with the sun. 10000mAh.", 
            29.99, 
            "/images/Solar Power Bank.jpg"
        );
        
        Product p3 = new Product(
            "Reusable Metal Straws", 
            "Set of 4 stainless steel straws + cleaner.", 
            8.50, 
            "/images/Metal Straws.jpg"
        );
        
        Product p4 = new Product(
            "LED Smart Bulb", 
            "Save 80% energy compared to normal bulbs.", 
            12.00, 
            "/images/LED Bulb.jpg"
        );

        productRepository.saveAll(Arrays.asList(p1, p2, p3, p4));
        System.out.println("✅ Marketplace Data Updated with Local Images!");
    }
}