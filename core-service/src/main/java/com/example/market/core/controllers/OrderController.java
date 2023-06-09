package com.example.market.core.controllers;

import com.example.market.api.dtos.OrderDto;
import com.example.market.core.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public void createOrder(@RequestHeader String username) {
        orderService.saveOrder(username);
    }

    @GetMapping
    public List<OrderDto> getOrderByUsername(@RequestHeader String username) {
        return orderService.getOrderByUser(username);
    }
}
