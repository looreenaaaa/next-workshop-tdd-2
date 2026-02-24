package com.example.ordersapi.adapters.outbound.persistence;

import com.example.ordersapi.domain.model.Player;
import org.springframework.stereotype.Component;

@Component
public class PlayerPersistenceMapper {

    public PlayerEntity toEntity(Player domain) {
        if (domain == null) {
            return null;
        }

        PlayerEntity entity = new PlayerEntity();

        entity.setId(domain.getId());
        entity.setName(domain.getName());
        entity.setTeam(domain.getTeam());
        entity.setNumber(domain.getNumber());
        entity.setPosition(domain.getPosition());
        return entity;
    }

    public Player toDomain(PlayerEntity entity) {
        if (entity == null) {
            return null;
        }

        Player domain = new Player();

        domain.setId(entity.getId());
        domain.setName(entity.getName());
        domain.setTeam(entity.getTeam());
        domain.setNumber(entity.getNumber());
        domain.setPosition(entity.getPosition());
        return domain;
    }
}
