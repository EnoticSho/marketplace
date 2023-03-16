package com.example.spring.marketplace.services;

import com.example.spring.marketplace.converters.OrderConverter;
import com.example.spring.marketplace.dtos.OrderDto;
import com.example.spring.marketplace.entities.Order;
import com.example.spring.marketplace.entities.OrderItem;
import com.example.spring.marketplace.entities.User;
import com.example.spring.marketplace.model.CartItem;
import com.example.spring.marketplace.repositories.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartService cartService;
    private final ProductService productService;
    private final OrderConverter orderConverter;

    @Autowired
    public OrderService(OrderRepository orderRepository, CartService cartService, ProductService productService, OrderConverter orderConverter) {
        this.orderRepository = orderRepository;
        this.cartService = cartService;
        this.productService = productService;
        this.orderConverter = orderConverter;
    }

    @Transactional
    public void saveOrder(User user) {
        List<CartItem> cartItemList = cartService.getCart().getCartItemList();
        Order order = new Order();
        order.setDate(new Date());
        order.setUser(user);
        List<OrderItem> orderItems = cartItemList.stream()
                .map(item -> new OrderItem(
                        productService.getProductById(item.getId()).get(),
                        order,
                        item.getQuantity(),
                        item.getPricePerCount())).toList();
        order.setItems(orderItems);
        orderRepository.save(order);
    }

    public List<OrderDto> getOrderByUser(User user) {
        List<Order> list = orderRepository.findByUser(user).orElseThrow(() -> new RuntimeException("не найдено"));
        return list.stream().map(orderConverter::entityToOrderDto).toList();
    }
}
