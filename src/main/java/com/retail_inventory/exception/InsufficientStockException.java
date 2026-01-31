package com.retail_inventory.exception;

public class InsufficientStockException extends RuntimeException {
    public InsufficientStockException(int available, int requested) {
        super("Insufficient stock. Available: " + available + ", Requested: " + requested);
    }
}
