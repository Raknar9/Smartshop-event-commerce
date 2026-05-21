package com.example.notificationservice.kafka;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderCreatedEvent {

    private Long orderId;

    private Long productId;

    private Integer quantity;

    private String customerName;

    private String status;
}