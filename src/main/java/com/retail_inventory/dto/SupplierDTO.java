package com.retail_inventory.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class SupplierDTO {

	 private Long id;

	    @NotBlank(message = "Supplier name must not be blank")
	    @Pattern(
	    	    regexp = "^[A-Za-z][A-Za-z0-9 ]*$",
	    	    message = "Name must start with a letter and can contain letters, numbers, and spaces"
	    	)
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

