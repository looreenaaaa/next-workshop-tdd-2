package com.example.ordersapi.adapters.inbound.rest.dto;

public class OrderResponseDto {

    private Long id;
    private String product;
    private int quantity;

    public OrderResponseDto(Long id, String product, int quantity) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public String getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }
}
