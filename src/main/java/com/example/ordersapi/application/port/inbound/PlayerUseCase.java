package com.example.ordersapi.application.port.inbound;

import com.example.ordersapi.domain.model.Player;

import java.util.List;

public interface PlayerUseCase {

    List<Player> getAll();

    Player create(Player player);

    Player getById(Long id);

    void delete(Long id);
}
