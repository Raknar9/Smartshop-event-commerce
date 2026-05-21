package com.example.paymentservice.kafka;

import lombok.RequiredArgsConstructor;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentConsumer {

    private final PaymentProducer paymentProducer;

    @KafkaListener(
            topics = "stock-reserved",
            groupId = "payment-group",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void consume(StockReservedEvent event) {

        System.out.println();
        System.out.println("========= PAYMENT PROCESSING =========");
        System.out.println("Order ID: " + event.getOrderId());

        System.out.println("PAYMENT COMPLETED");

        PaymentCompletedEvent paymentEvent =
                PaymentCompletedEvent.builder()
                        .orderId(event.getOrderId())
                        .status("PAID")
                        .build();

        paymentProducer.sendPaymentCompletedEvent(
                paymentEvent
        );

        System.out.println("PAYMENT EVENT SENT");

        System.out.println("======================================");
        System.out.println();
    }
}