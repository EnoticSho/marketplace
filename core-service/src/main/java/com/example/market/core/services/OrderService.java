package com.example.market.core.services;

import com.example.market.api.dtos.CartItemDto;
import com.example.market.api.dtos.OrderDto;
import com.example.market.core.converters.OrderConverter;
import com.example.market.core.entities.Order;
import com.example.market.core.entities.OrderItem;
import com.example.market.core.integrations.CartIntegrationService;
import com.example.market.core.repositories.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartIntegrationService cartIntegrationService;
    private final ProductService productService;
    private final OrderConverter orderConverter;

    @Autowired
    public OrderService(OrderRepository orderRepository, CartIntegrationService cartIntegrationService, ProductService productService, OrderConverter orderConverter) {
        this.orderRepository = orderRepository;
        this.cartIntegrationService = cartIntegrationService;
        this.productService = productService;
        this.orderConverter = orderConverter;
    }

    @Transactional
    public void saveOrder(String username) {
        List<CartItemDto> cartItemList = cartIntegrationService.getCurrentCart(username).getCartItemList();
        Order order = new Order();
        order.setDate(new Date());
        order.setUsername(username);
        List<OrderItem> orderItems = cartItemList.stream()
                .map(item -> new OrderItem(
                        productService.getProductById(item.getId()).get(),
                        order,
                        item.getQuantity(),
                        item.getPricePerCount())).toList();
        order.setItems(orderItems);
        orderRepository.save(order);
        cartIntegrationService.clear(username);
    }

    public List<OrderDto> getOrderByUser(String username) {
        List<Order> list = orderRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("не найдено"));
        return list.stream().map(orderConverter::entityToOrderDto).toList();
    }
}
