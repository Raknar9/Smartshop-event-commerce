package com.example.paymentservice.kafka;

import lombok.RequiredArgsConstructor;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentProducer {

    private final KafkaTemplate<
            String,
            PaymentCompletedEvent
            > kafkaTemplate;

    private static final String TOPIC =
            "payment-completed";

    public void sendPaymentCompletedEvent(
            PaymentCompletedEvent event
    ) {

        kafkaTemplate.send(TOPIC, event);

        System.out.println(
                "PAYMENT EVENT SENT"
        );
    }
}