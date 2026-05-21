package com.example.inventoryservice.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryProducer {

    private final KafkaTemplate<String, StockReservedEvent> kafkaTemplate;

    private static final String TOPIC = "stock-reserved";

    public void sendStockReservedEvent(StockReservedEvent event) {

        kafkaTemplate.send(TOPIC, event);

        System.out.println("STOCK EVENT SENT");
    }
}