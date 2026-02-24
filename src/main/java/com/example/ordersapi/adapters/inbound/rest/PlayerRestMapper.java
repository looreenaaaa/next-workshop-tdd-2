package com.example.ordersapi.adapters.inbound.rest;

import com.example.ordersapi.adapters.inbound.rest.dto.PlayerRequestDto;
import com.example.ordersapi.adapters.inbound.rest.dto.PlayerResponseDto;
import com.example.ordersapi.domain.model.Player;

public class PlayerRestMapper {

    public Player toDomain(PlayerRequestDto dto) {
        Player player = new Player();
        player.setName(dto.getName());
        player.setTeam(dto.getTeam());
        player.setNumber(dto.getNumber());
        player.setPosition(dto.getPosition());

        return player;
    }

    public PlayerResponseDto toResponse(Player player) {
        return new PlayerResponseDto(
                player.getId(),
                player.getName(),
                player.getTeam(),
                player.getNumber(),
                player.getPosition()
        );
    }
}
