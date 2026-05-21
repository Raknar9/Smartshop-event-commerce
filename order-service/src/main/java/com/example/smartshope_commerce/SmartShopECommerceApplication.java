package com.example.smartshope_commerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication
public class SmartShopECommerceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartShopECommerceApplication.class, args);
    }

}
