package com.example.ordersapi.adapters.outbound.persistence;

import com.example.ordersapi.domain.model.Order;

public class OrderPersistenceMapper {

    public OrderEntity toEntity(Order domain) {
        if (domain == null) {
            return null;
        }

        OrderEntity entity = new OrderEntity();
        entity.setId(domain.getId());
        entity.setProduct(domain.getProduct());
        entity.setQuantity(domain.getQuantity());
        return entity;
    }

    public Order toDomain(OrderEntity entity) {
        if (entity == null) {
            return null;
        }

        Order domain = new Order();
        domain.setId(entity.getId());
        domain.setProduct(entity.getProduct());
        domain.setQuantity(entity.getQuantity());
        return domain;
    }
}
