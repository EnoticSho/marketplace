package com.example.market.core.services;

import com.example.market.core.repositories.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemService {

    private final OrderItemRepository orderItemRepostory;

    @Autowired
    public OrderItemService(OrderItemRepository orderItemRepository) {
        this.orderItemRepostory = orderItemRepository;
    }
}
