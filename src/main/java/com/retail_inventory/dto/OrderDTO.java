package com.retail_inventory.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class OrderDTO {

	 private Long id;

	    @NotNull(message = "Product ID is required")
	    private Long productId;

	    @Min(value = 1, message = "Order quantity must be at least 1")
	    private int quantity;

	    private LocalDateTime orderDate;

	    // --- Getters and Setters ---
	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public Long getProductId() {
	        return productId;
	    }

	    public void setProductId(Long productId) {
	        this.productId = productId;
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
	}