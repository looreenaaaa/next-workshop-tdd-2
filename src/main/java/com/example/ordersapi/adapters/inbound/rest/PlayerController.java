package com.example.ordersapi.adapters.inbound.rest;

import com.example.ordersapi.adapters.inbound.rest.dto.PlayerRequestDto;
import com.example.ordersapi.adapters.inbound.rest.dto.PlayerResponseDto;
import com.example.ordersapi.application.port.inbound.PlayerUseCase;
import com.example.ordersapi.domain.model.Player;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerController {

    private final PlayerUseCase useCase;
    private final PlayerRestMapper mapper = new PlayerRestMapper();

    public PlayerController(PlayerUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping
    public List<PlayerResponseDto> getAll() {
        return useCase.getAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PlayerResponseDto create(@RequestBody PlayerRequestDto request) {
        Player player = mapper.toDomain(request);
        Player created = useCase.create(player);
        return mapper.toResponse(created);
    }

    @GetMapping("/{id}")
    public PlayerResponseDto getById(@PathVariable Long id) {
        Player player = useCase.getById(id);
        return mapper.toResponse(player);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        useCase.delete(id);
    }
}
