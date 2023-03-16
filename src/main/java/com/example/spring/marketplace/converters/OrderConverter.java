package com.example.spring.marketplace.converters;

import com.example.spring.marketplace.dtos.OrderDto;
import com.example.spring.marketplace.entities.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderConverter {

    private final OrderItemConverter orderItemConverter;

    @Autowired
    public OrderConverter(OrderItemConverter orderItemConverter) {
        this.orderItemConverter = orderItemConverter;
    }

    public OrderDto entityToOrderDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setUser(order.getUser().getUsername());
        orderDto.setItemsDtos(order.getItems().stream().map(orderItemConverter::entityToOrderItemDto).toList());
        orderDto.setDate(order.getDate());
        return orderDto;
    }
}
