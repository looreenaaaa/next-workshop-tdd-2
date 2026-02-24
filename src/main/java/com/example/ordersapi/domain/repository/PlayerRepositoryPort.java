package com.example.ordersapi.domain.repository;

import com.example.ordersapi.domain.model.Player;

import java.util.List;
import java.util.Optional;

public interface PlayerRepositoryPort {
    List<Player> findAll();

    Optional<Player> findById(Long id);

    Player save(Player player);

    void delete(Player player);
}
