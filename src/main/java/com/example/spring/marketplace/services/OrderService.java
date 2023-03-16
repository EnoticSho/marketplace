package com.example.spring.marketplace.services;

import com.example.spring.marketplace.dtos.OrderDto;
import com.example.spring.marketplace.entities.Order;
import com.example.spring.marketplace.entities.OrderItems;
import com.example.spring.marketplace.entities.User;
import com.example.spring.marketplace.model.CartItem;
import com.example.spring.marketplace.repositories.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartService cartService;
    private final ProductService productService;

    @Autowired
    public OrderService(OrderRepository orderRepository, CartService cartService, ProductService productService) {
        this.orderRepository = orderRepository;
        this.cartService = cartService;
        this.productService = productService;
    }

    @Transactional
    public void saveOrder(User user) {
        List<CartItem> cartItemList = cartService.getCart().getCartItemList();
        Order order = new Order();
        order.setDate(new Date());
        order.setUser(user);
        List<OrderItems> orderItems = cartItemList.stream()
                .map(item -> new OrderItems(
                        productService.getProductById(item.getId()).get(),
                        order,
                        item.getQuantity(),
                        item.getPricePerCount())).toList();
        order.setItems(orderItems);
        orderRepository.save(order);
    }

    public OrderDto getOrderByUser(User user) {
        Order order = orderRepository.findByUser(user).orElseThrow(() -> new RuntimeException("не найдено"));
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setDate(order.getDate());
        orderDto.setUser(order.getUser().getUsername());
        orderDto.setItemsDtos(null);
        return orderDto;
    }
}
