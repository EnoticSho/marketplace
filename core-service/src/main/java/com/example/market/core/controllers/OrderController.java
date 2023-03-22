package com.example.market.core.controllers;

import com.example.market.api.dtos.OrderDto;
import com.example.market.core.entities.User;
import com.example.market.core.services.OrderService;
import com.example.market.core.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
@CrossOrigin("*")
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
