package com.example.smartshope_commerce.kafka;


import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderProducer {

    private final KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate;

    private static final String TOPIC = "order-created";

    public void sendOrderCreatedEvent(OrderCreatedEvent event) {

        kafkaTemplate.send(TOPIC, event);

        System.out.println("EVENT SENT TO KAFKA: " + event);
    }
}