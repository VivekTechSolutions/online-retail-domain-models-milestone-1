package com.retail_inventory.service;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;
import com.retail_inventory.entity.Order;
import com.retail_inventory.entity.Product;
import com.retail_inventory.exception.InsufficientStockException;
import com.retail_inventory.exception.OrderNotFoundException;
import com.retail_inventory.exception.ProductNotFoundException;
import com.retail_inventory.repository.OrderRepository;
import com.retail_inventory.repository.ProductRepository;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository,
                        ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    public Order createOrder(Long productId, int quantity) {

        // Find product
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));

        // Check stock
        if (product.getStockQuantity() < quantity) {
            throw new InsufficientStockException(product.getStockQuantity(), quantity);
        }

        // Reduce stock
        product.setStockQuantity(product.getStockQuantity() - quantity);
        productRepository.save(product);

        // Create order using constructor
        Order order = new Order(product, quantity, LocalDateTime.now());

        // Save and return
        return orderRepository.save(order);
    }


    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public void deleteOrder(Long id) {
        if (!orderRepository.existsById(id))
            throw new OrderNotFoundException(id);
        orderRepository.deleteById(id);
    }
}
