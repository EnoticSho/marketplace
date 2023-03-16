package com.example.spring.marketplace.services;

import com.example.spring.marketplace.repositories.OrderItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemsService {

    private final OrderItemsRepository orderItemsRepostory;

    @Autowired
    public OrderItemsService(OrderItemsRepository orderItemsRepository) {
        this.orderItemsRepostory = orderItemsRepository;
    }
}
