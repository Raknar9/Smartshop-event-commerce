package com.example.notificationservice.kafka;


import com.example.notificationservice.realtime.SseService;
import lombok.RequiredArgsConstructor;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationConsumer {

    private final SseService sseService;

    @KafkaListener(
            topics = "order-created",
            groupId = "notification-group"
    )
    public void orderCreated(String event) {

        System.out.println(
                "ORDER CREATED EVENT: " + event
        );

        sseService.sendEvent(
                "order-created",
                event
        );
    }

    @KafkaListener(
            topics = "stock-reserved",
            groupId = "notification-group"
    )
    public void stockReserved(String event) {

        System.out.println(
                "STOCK RESERVED EVENT: " + event
        );

        sseService.sendEvent(
                "stock-reserved",
                event
        );
    }

    @KafkaListener(
            topics = "payment-completed",
            groupId = "notification-group"
    )
    public void paymentCompleted(String event) {

        System.out.println(
                "PAYMENT COMPLETED EVENT: " + event
        );

        sseService.sendEvent(
                "payment-completed",
                event
        );
    }
}