package com.example.smartshope_commerce.kafka;

import com.example.smartshope_commerce.entity.Order;
import com.example.smartshope_commerce.repository.OrderRepository;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderStatusConsumer {

    private final OrderRepository orderRepository;

    private final ObjectMapper objectMapper =
            new ObjectMapper();

    @KafkaListener(
            topics = "stock-reserved",
            groupId = "order-status-group"
    )
    public void stockReserved(
            String event
    ) {

        updateOrderStatus(
                event,
                "RESERVED"
        );
    }

    @KafkaListener(
            topics = "payment-completed",
            groupId = "order-status-group"
    )
    public void paymentCompleted(
            String event
    ) {

        updateOrderStatus(
                event,
                "PAID"
        );
    }

    private void updateOrderStatus(
            String event,
            String status
    ) {

        try {

            JsonNode json =
                    objectMapper.readTree(event);

            Long orderId =
                    json.get("orderId").asLong();

            Order order =
                    orderRepository.findById(orderId)
                            .orElseThrow();

            order.setStatus(status);

            orderRepository.save(order);

            System.out.println(
                    "ORDER STATUS UPDATED: "
                            + status
            );

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}