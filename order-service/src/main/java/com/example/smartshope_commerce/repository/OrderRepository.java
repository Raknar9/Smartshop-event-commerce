package com.example.smartshope_commerce.repository;


import com.example.smartshope_commerce.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}