package com.example.ordersapi.adapters.outbound.persistence;

import com.example.ordersapi.domain.model.Order;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class OrderPersistenceMapperTest {

    private final OrderPersistenceMapper mapper = new OrderPersistenceMapper();

    @Test
    void toEntity_should_map_domain_to_entity() {
        Order domain = new Order();
        domain.setId(1L);
        domain.setProduct("Book");
        domain.setQuantity(2);

        OrderEntity entity = mapper.toEntity(domain);

        assertThat(entity).isNotNull();
        assertThat(entity.getId()).isEqualTo(1L);
        assertThat(entity.getProduct()).isEqualTo("Book");
        assertThat(entity.getQuantity()).isEqualTo(2);
    }

    @Test
    void toDomain_should_map_entity_to_domain() {
        OrderEntity entity = new OrderEntity();
        entity.setId(1L);
        entity.setProduct("Pen");
        entity.setQuantity(5);

        Order domain = mapper.toDomain(entity);

        assertThat(domain).isNotNull();
        assertThat(domain.getId()).isEqualTo(1L);
        assertThat(domain.getProduct()).isEqualTo("Pen");
        assertThat(domain.getQuantity()).isEqualTo(5);
    }

    @Test
    void toEntity_should_return_null_when_domain_is_null() {
        OrderEntity entity = mapper.toEntity(null);

        assertThat(entity).isNull();
    }

    @Test
    void toDomain_should_return_null_when_entity_is_null() {
        Order domain = mapper.toDomain(null);

        assertThat(domain).isNull();
    }
}
