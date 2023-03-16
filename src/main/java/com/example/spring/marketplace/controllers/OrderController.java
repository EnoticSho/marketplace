package com.example.spring.marketplace.controllers;

import com.example.spring.marketplace.dtos.OrderDto;
import com.example.spring.marketplace.entities.User;
import com.example.spring.marketplace.services.OrderService;
import com.example.spring.marketplace.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;

    @Autowired
    public OrderController(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @PostMapping
    public void createOrder(Principal principal) {
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
        orderService.saveOrder(user);
    }

    @GetMapping
    public List<OrderDto> getOrderByUsername(Principal principal) {
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
        return orderService.getOrderByUser(user);
    }
}
