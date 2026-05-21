package com.example.inventoryservice.kafka;


import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryConsumer {

    private final InventoryProducer inventoryProducer;

    @KafkaListener(
            topics = "order-created",
            groupId = "inventory-group",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void consume(OrderCreatedEvent event) {

        System.out.println("========== EVENT RECEIVED ==========");
        System.out.println("Order ID: " + event.getOrderId());
        System.out.println("Product ID: " + event.getProductId());
        System.out.println("Quantity: " + event.getQuantity());

        System.out.println("STOCK RESERVED");

        StockReservedEvent stockEvent =
                StockReservedEvent.builder()
                        .orderId(event.getOrderId())
                        .productId(event.getProductId())
                        .quantity(event.getQuantity())
                        .customerName(event.getCustomerName())
                        .status("RESERVED")
                        .build();

        inventoryProducer.sendStockReservedEvent(stockEvent);
    }
}

