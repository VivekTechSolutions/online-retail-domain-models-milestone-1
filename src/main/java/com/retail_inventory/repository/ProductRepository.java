package com.retail_inventory.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.retail_inventory.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // Find product by name 
    Optional<Product> findByName(String name);

    // Check if product exists by name
    boolean existsByName(String name);
}