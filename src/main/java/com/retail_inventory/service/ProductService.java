package com.retail_inventory.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.retail_inventory.dto.ProductDTO;
import com.retail_inventory.entity.Product;
import com.retail_inventory.entity.Supplier;
import com.retail_inventory.exception.ProductNotFoundException;
import com.retail_inventory.repository.ProductRepository;
import com.retail_inventory.repository.SupplierRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final SupplierRepository supplierRepository; // needed for linking supplier

    public ProductService(ProductRepository productRepository, SupplierRepository supplierRepository) {
        this.productRepository = productRepository;
        this.supplierRepository = supplierRepository;
    }

    public Product createProduct(ProductDTO productDTO) {
        Supplier supplier = supplierRepository.findById(productDTO.getSupplierId())
                .orElseThrow(() -> new RuntimeException("Supplier not found with ID " + productDTO.getSupplierId()));

        Product product = new Product();
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setStockQuantity(productDTO.getStockQuantity());
        product.setSupplier(supplier);

        return productRepository.save(product);
    }

    public ProductDTO getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with ID " + id));
        return mapToDTO(product);
    }

    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(this::mapToDTO)
                .toList();
    }

    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id))
            throw new RuntimeException("Product not found with ID " + id);
        productRepository.deleteById(id);
    }

    private ProductDTO mapToDTO(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        dto.setStockQuantity(product.getStockQuantity());
        dto.setSupplierId(product.getSupplier().getId());
        return dto;
    }
}
