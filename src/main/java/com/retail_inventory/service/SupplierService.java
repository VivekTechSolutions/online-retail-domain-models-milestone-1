package com.retail_inventory.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.retail_inventory.dto.SupplierDTO;
import com.retail_inventory.entity.Supplier;
import com.retail_inventory.exception.SupplierNotFoundException;
import com.retail_inventory.repository.SupplierRepository;

@Service
public class SupplierService {

    private final SupplierRepository supplierRepository;

    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    // Create Supplier with duplicate check
    public SupplierDTO createSupplier(SupplierDTO supplierDTO) {
        if (supplierRepository.existsByName(supplierDTO.getName())) {
            throw new RuntimeException("Supplier already exists with name: " + supplierDTO.getName());
        }

        Supplier supplier = new Supplier();
        supplier.setName(supplierDTO.getName());

        Supplier saved = supplierRepository.save(supplier);

        return mapToDTO(saved);
    }

    public SupplierDTO getSupplierById(Long id) {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new SupplierNotFoundException(id));
        return mapToDTO(supplier);
    }

    public List<SupplierDTO> getAllSuppliers() {
        return supplierRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public void deleteSupplier(Long id) {
        if (!supplierRepository.existsById(id))
            throw new SupplierNotFoundException(id);
        supplierRepository.deleteById(id);
    }

    private SupplierDTO mapToDTO(Supplier supplier) {
        SupplierDTO dto = new SupplierDTO();
        dto.setId(supplier.getId());
        dto.setName(supplier.getName());
        return dto;
    }
}
