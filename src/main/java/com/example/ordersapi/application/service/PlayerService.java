package com.example.ordersapi.application.service;

import com.example.ordersapi.application.port.inbound.PlayerUseCase;
import com.example.ordersapi.domain.exception.PlayerNotFoundException;
import com.example.ordersapi.domain.model.Player;
import com.example.ordersapi.domain.repository.PlayerRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService implements PlayerUseCase {

    private final PlayerRepositoryPort repository;

    public PlayerService(PlayerRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public List<Player> getAll() {
        return repository.findAll();
    }

    @Override
    public Player create(Player player) {
        return repository.save(player);
    }

    @Override
    public Player getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new PlayerNotFoundException(id));
    }

    @Override
    public void delete(Long id) {
        Player player = repository.findById(id)
                .orElseThrow(() -> new PlayerNotFoundException(id));
        repository.delete(player);
    }

    @Override
    public Player update(Long id, Player player) {
        Player existingPlayer = repository.findById(id)
                .orElseThrow(() -> new PlayerNotFoundException(id));

        existingPlayer.setName(player.getName());
        existingPlayer.setTeam(player.getTeam());
        existingPlayer.setNumber(player.getNumber());
        existingPlayer.setPosition(player.getPosition());

        return repository.save(existingPlayer);
    }
}
