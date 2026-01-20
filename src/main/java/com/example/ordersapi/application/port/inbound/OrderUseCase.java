package com.example.ordersapi.application.port.inbound;

import com.example.ordersapi.domain.model.Order;

import java.util.List;

public interface OrderUseCase {

    List<Order> getAll();

    Order create(Order order);

    Order getById(Long id);

    void delete(Long id);
}
