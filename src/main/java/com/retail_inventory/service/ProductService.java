package com.retail_inventory.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.retail_inventory.entity.Product;
import com.retail_inventory.exception.ProductNotFoundException;
import com.retail_inventory.repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    // Constructor injection
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    public Product getProductByName(String name) {
        return productRepository.findByName(name)
                .orElseThrow(() ->
                        new ProductNotFoundException("Product not found with name: " + name));
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException(id);
        }
        productRepository.deleteById(id);
    }
}
