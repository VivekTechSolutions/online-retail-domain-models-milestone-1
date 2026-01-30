package com.retail_inventory.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.retail_inventory.entity.Supplier;
import com.retail_inventory.exception.SupplierNotFoundException;
import com.retail_inventory.repository.SupplierRepository;

@Service
public class SupplierService {

    private final SupplierRepository supplierRepository;

    // Constructor injection
    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public Supplier createSupplier(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    public Supplier getSupplierById(Long id) {
        return supplierRepository.findById(id)
                .orElseThrow(() -> new SupplierNotFoundException(id));
    }

    public Supplier getSupplierByName(String name) {
        return supplierRepository.findByName(name)
                .orElseThrow(() ->
                        new SupplierNotFoundException(
                                "Supplier not found with name: " + name));
    }

    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    public void deleteSupplier(Long id) {
        if (!supplierRepository.existsById(id)) {
            throw new SupplierNotFoundException(id);
        }
        supplierRepository.deleteById(id);
    }
}
