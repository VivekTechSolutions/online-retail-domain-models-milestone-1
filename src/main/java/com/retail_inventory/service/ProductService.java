package com.retail_inventory.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.retail_inventory.dto.ProductDTO;
import com.retail_inventory.entity.Product;
import com.retail_inventory.entity.Supplier;
import com.retail_inventory.exception.ProductAlreadyExistsException;
import com.retail_inventory.exception.ProductNotFoundException;
import com.retail_inventory.exception.SupplierNotFoundException;
import com.retail_inventory.repository.ProductRepository;
import com.retail_inventory.repository.SupplierRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final SupplierRepository supplierRepository;

    public ProductService(ProductRepository productRepository, SupplierRepository supplierRepository) {
        this.productRepository = productRepository;
        this.supplierRepository = supplierRepository;
    }

    // Create product with supplier validation and duplicate check
    public ProductDTO createProduct(ProductDTO productDTO) {
        Supplier supplier = supplierRepository.findById(productDTO.getSupplierId())
                .orElseThrow(() -> new SupplierNotFoundException(productDTO.getSupplierId()));

        if (productRepository.existsByName(productDTO.getName())) {
        	 throw new ProductAlreadyExistsException(productDTO.getName());
        }

        Product product = new Product();
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setStockQuantity(productDTO.getStockQuantity());
        product.setSupplier(supplier);

        Product saved = productRepository.save(product);
        return mapToDTO(saved);
    }

    public ProductDTO getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        return mapToDTO(product);
    }

    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id))
            throw new ProductNotFoundException(id);
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
