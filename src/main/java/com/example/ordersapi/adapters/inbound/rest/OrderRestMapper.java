package com.example.ordersapi.adapters.inbound.rest;

import com.example.ordersapi.adapters.inbound.rest.dto.OrderRequestDto;
import com.example.ordersapi.adapters.inbound.rest.dto.OrderResponseDto;
import com.example.ordersapi.domain.model.Order;

public class OrderRestMapper {

    public Order toDomain(OrderRequestDto dto) {
        Order order = new Order();
        order.setProduct(dto.getProduct());
        order.setQuantity(dto.getQuantity());
        return order;
    }

    public OrderResponseDto toResponse(Order order) {
        return new OrderResponseDto(
                order.getId(),
                order.getProduct(),
                order.getQuantity()
        );
    }
}
