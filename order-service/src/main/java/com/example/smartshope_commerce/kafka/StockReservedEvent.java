package com.example.smartshope_commerce.kafka;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockReservedEvent {

    private Long orderId;

    private Long productId;

    private Integer quantity;

    private String customerName;

    private String status;
}