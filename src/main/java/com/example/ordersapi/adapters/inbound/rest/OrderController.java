package com.example.ordersapi.adapters.inbound.rest;

import com.example.ordersapi.adapters.inbound.rest.dto.OrderRequestDto;
import com.example.ordersapi.adapters.inbound.rest.dto.OrderResponseDto;
import com.example.ordersapi.application.service.OrderService;
import com.example.ordersapi.domain.model.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService service;
    private final OrderRestMapper mapper = new OrderRestMapper();

    public OrderController(OrderService service) {
        this.service = service;
    }

    @GetMapping
    public List<OrderResponseDto> getAll() {
        return service.getAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponseDto create(@RequestBody OrderRequestDto request) {
        Order order = mapper.toDomain(request);
        Order created = service.create(order);
        return mapper.toResponse(created);
    }

    @GetMapping("/{id}")
    public OrderResponseDto getById(@PathVariable Long id) {
        Order order = service.getById(id);
        return mapper.toResponse(order);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
