package com.example.market.core.converters;

import com.example.market.api.dtos.OrderItemDto;
import com.example.market.core.entities.OrderItem;
import org.springframework.stereotype.Component;

@Component
public class OrderItemConverter {
    public OrderItemDto entityToOrderItemDto(OrderItem orderItem) {
        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setId(orderItem.getId());
        orderItemDto.setOrder_id(orderItem.getOrder().getId());
        orderItemDto.setProductName(orderItem.getProduct().getTitle());
        orderItemDto.setQuantity(orderItem.getCount());
        orderItemDto.setCost(orderItem.getPrice());
        return orderItemDto;
    }
}
