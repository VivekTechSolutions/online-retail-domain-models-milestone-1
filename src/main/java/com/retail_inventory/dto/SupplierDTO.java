package com.retail_inventory.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SupplierDTO {
    private Long id;
    private String name;
}
