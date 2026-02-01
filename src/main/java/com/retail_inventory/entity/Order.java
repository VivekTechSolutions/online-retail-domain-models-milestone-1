package com.retail_inventory.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

/**
 * Represents an order placed in the retail system.
 */
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // The product associated with this order
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    @JsonIgnore 
    private Product product;

    // Quantity of the product in this order
    @Column(nullable = false)
    private int quantity;

    // Date and time when the order was created
    @Column(name = "order_date", nullable = false)
    private LocalDateTime orderDate;

    // --- Constructors ---

    // No-argument constructor required by JPA 
    public Order() {
    }

  
    public Order(Product product, int quantity, LocalDateTime orderDate) {
        this.product = product;
        this.quantity = quantity;
        this.orderDate = orderDate;
    }

    // --- Getters and Setters ---

    public Long getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    // --- Utility Methods (optional) ---

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", product=" + (product != null ? product.getName() : "null") +
                ", quantity=" + quantity +
                ", orderDate=" + orderDate +
                '}';
    }
}
