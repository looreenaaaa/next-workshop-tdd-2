package com.example.ordersapi.application.service;

import com.example.ordersapi.application.port.inbound.OrderUseCase;
import com.example.ordersapi.domain.exception.OrderNotFoundException;
import com.example.ordersapi.domain.model.Order;
import com.example.ordersapi.domain.repository.OrderRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService implements OrderUseCase {

    private final OrderRepositoryPort repository;

    public OrderService(OrderRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public List<Order> getAll() {
        return repository.findAll();
    }

    @Override
    public Order create(Order order) {
        return repository.save(order);
    }

    @Override
    public Order getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));
    }

    @Override
    public void delete(Long id) {
        Order order = repository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));
        repository.delete(order);
    }
}
