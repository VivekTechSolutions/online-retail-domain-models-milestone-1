package com.retail_inventory.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {
    private Long id;
    private String name;
    private Double price;
    private Integer stockQuantity;
    private Long supplierId;
}
