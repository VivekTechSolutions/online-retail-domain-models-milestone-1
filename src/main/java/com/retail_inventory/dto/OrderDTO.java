package com.retail_inventory.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDTO {
    private Long id;
    private Long productId;
    private Integer quantity;
    private LocalDateTime orderDate;
}
