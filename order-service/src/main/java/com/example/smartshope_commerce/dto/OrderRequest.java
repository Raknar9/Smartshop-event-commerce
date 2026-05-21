package com.example.smartshope_commerce.dto;

import lombok.Data;

@Data
public class OrderRequest {

    private Long productId;

    private Integer quantity;

    private String customerName;
}