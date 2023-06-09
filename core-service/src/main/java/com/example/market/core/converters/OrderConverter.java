package com.example.market.core.converters;

import com.example.market.api.dtos.OrderDto;
import com.example.market.core.entities.Order;
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
        orderDto.setUsername(order.getUsername());
        orderDto.setItemsDto(order.getItems().stream().map(orderItemConverter::entityToOrderItemDto).toList());
        orderDto.setDate(order.getDate());
        return orderDto;
    }
}
