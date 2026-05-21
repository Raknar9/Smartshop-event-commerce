package com.example.smartshope_commerce.service;

import com.example.smartshope_commerce.dto.OrderRequest;
import com.example.smartshope_commerce.entity.Order;
import com.example.smartshope_commerce.kafka.OrderCreatedEvent;
import com.example.smartshope_commerce.kafka.OrderProducer;
import com.example.smartshope_commerce.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final OrderProducer orderProducer;

    public Order createOrder(OrderRequest request) {

        Order order = Order.builder()
                .productId(request.getProductId())
                .quantity(request.getQuantity())
                .customerName(request.getCustomerName())
                .status("CREATED")
                .createdAt(LocalDateTime.now())
                .build();

        Order savedOrder =
                orderRepository.save(order);

        OrderCreatedEvent event =
                OrderCreatedEvent.builder()
                        .orderId(savedOrder.getId())
                        .productId(savedOrder.getProductId())
                        .quantity(savedOrder.getQuantity())
                        .customerName(savedOrder.getCustomerName())
                        .status(savedOrder.getStatus())
                        .build();

        orderProducer.sendOrderCreatedEvent(event);

        return savedOrder;
    }

    public List<Order> getAllOrders() {

        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {

        return orderRepository.findById(id)
                .orElseThrow(
                        () -> new RuntimeException(
                                "Order not found"
                        )
                );
    }
}