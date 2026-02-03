package com.retail_inventory.dto;

import jakarta.validation.constraints.NotBlank;

public class SupplierDTO {

	 private Long id;

	    @NotBlank(message = "Supplier name must not be blank")
	    private String name;

	    // --- Getters and Setters ---
	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }
	}

