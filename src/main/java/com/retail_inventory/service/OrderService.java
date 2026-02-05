package com.retail_inventory.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.retail_inventory.dto.OrderDTO;
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

    @Transactional
    public OrderDTO createOrder(OrderDTO orderDTO) {
        Product product = productRepository.findById(orderDTO.getProductId())
                .orElseThrow(() -> new ProductNotFoundException(orderDTO.getProductId()));

        if (product.getStockQuantity() < orderDTO.getQuantity()) {
            throw new InsufficientStockException(product.getStockQuantity(), orderDTO.getQuantity());
        }

        product.setStockQuantity(product.getStockQuantity() - orderDTO.getQuantity());
        productRepository.save(product);

        Order order = new Order(product, orderDTO.getQuantity(), LocalDateTime.now());
        Order savedOrder = orderRepository.save(order);

        return mapToDTO(savedOrder);
    }


    public OrderDTO getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));
        return mapToDTO(order);
    }

    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(this::mapToDTO)
                .toList();
    }

    public void deleteOrder(Long id) {
        if (!orderRepository.existsById(id))
            throw new OrderNotFoundException(id);
        orderRepository.deleteById(id);
    }

    private OrderDTO mapToDTO(Order order) {
        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setProductId(order.getProduct().getId());
        dto.setQuantity(order.getQuantity());
        dto.setOrderDate(order.getOrderDate());
        return dto;
    }
}
