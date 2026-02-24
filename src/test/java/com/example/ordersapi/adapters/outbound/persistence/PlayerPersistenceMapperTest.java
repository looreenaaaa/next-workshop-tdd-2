package com.example.ordersapi.adapters.outbound.persistence;

import com.example.ordersapi.domain.model.Player;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerPersistenceMapperTest {

    private final PlayerPersistenceMapper mapper = new PlayerPersistenceMapper();

    @Test
    void toEntity_should_map_domain_to_entity() {
        Player domain = new Player();
        domain.setName("Lamine Yamal");
        domain.setTeam("Barcelona");
        domain.setNumber(19);
        domain.setPosition("Forward");

        PlayerEntity entity = mapper.toEntity(domain);

        assertThat(entity).isNotNull();
        assertThat(entity.getName()).isEqualTo("Lamine Yamal");
        assertThat(entity.getTeam()).isEqualTo("Barcelona");
        assertThat(entity.getNumber()).isEqualTo(19);
        assertThat(entity.getPosition()).isEqualTo("Forward");
    }

    @Test
    void toDomain_should_map_entity_to_domain() {
        PlayerEntity entity = new PlayerEntity();
        entity.setId(2L);
        entity.setName("Camavinga");
        entity.setTeam("Real Madrid");
        entity.setNumber(12);
        entity.setPosition("Midfielder");

        Player domain = mapper.toDomain(entity);

        assertThat(domain).isNotNull();
        assertThat(domain.getId()).isEqualTo(2L);
        assertThat(domain.getName()).isEqualTo("Camavinga");
        assertThat(domain.getTeam()).isEqualTo("Real Madrid");
        assertThat(domain.getNumber()).isEqualTo(12);
        assertThat(domain.getPosition()).isEqualTo("Midfielder");
    }

    @Test
    void toEntity_should_return_null_when_domain_is_null() {
        PlayerEntity entity = mapper.toEntity(null);

        assertThat(entity).isNull();
    }

    @Test
    void toDomain_should_return_null_when_entity_is_null() {
        Player domain = mapper.toDomain(null);

        assertThat(domain).isNull();
    }
}
