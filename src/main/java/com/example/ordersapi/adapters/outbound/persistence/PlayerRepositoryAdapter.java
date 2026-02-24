package com.example.ordersapi.adapters.outbound.persistence;

import com.example.ordersapi.domain.model.Player;
import com.example.ordersapi.domain.repository.PlayerRepositoryPort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PlayerRepositoryAdapter implements PlayerRepositoryPort {

    private final JpaPlayerRepository jpaRepository;
    private final PlayerPersistenceMapper mapper;

    public PlayerRepositoryAdapter(JpaPlayerRepository jpaRepository, PlayerPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public List<Player> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Player> findById(Long id) {
        return jpaRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public Player save(Player player) {
        PlayerEntity entity = mapper.toEntity(player);
        PlayerEntity saved = jpaRepository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public void delete(Player player) {
        PlayerEntity entity = mapper.toEntity(player);
        jpaRepository.delete(entity);
    }
}
