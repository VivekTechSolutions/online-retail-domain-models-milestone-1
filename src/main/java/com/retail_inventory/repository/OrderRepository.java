package com.retail_inventory.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.retail_inventory.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    // Fetch all orders for a product
    List<Order> findByProductId(Long productId);
}
