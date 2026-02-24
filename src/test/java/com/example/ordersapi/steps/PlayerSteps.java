package com.example.ordersapi.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerSteps {

    @Autowired
    private TestRestTemplate restTemplate;

    private Map<String, Object> playerRequest;
    private Long playerId;
    private ResponseEntity<?> response;

    // ---------- GIVEN ----------

    @Given("a player with name {string}, team {string}, number {int} and position {string}")
    public void playerWithNameTeamNumberAndPosition(String name, String team, Integer number, String position) {
        playerRequest = new HashMap<>();
        playerRequest.put("name", name);
        playerRequest.put("team", team);
        playerRequest.put("number", number);
        playerRequest.put("position", position);
    }

    @Given("an existing player with name {string}, team {string}, number {int} and position {string}")
    public void anExistingPlayerWithNameTeamNumberAndPosition(String name, String team, Integer number, String position) {
        playerRequest = new HashMap<>();
        playerRequest.put("name", name);
        playerRequest.put("team", team);
        playerRequest.put("number", number);
        playerRequest.put("position", position);

        ResponseEntity<Map> createResponse = restTemplate.postForEntity("/players", playerRequest, Map.class);

        this.playerId = ((Number) createResponse.getBody().get("id")).longValue();
    }

    // ---------- WHEN ----------

    @When("the player is saved")
    public void whenPlayerIsSaved() {
        ResponseEntity<Map> createResponse =
                restTemplate.postForEntity("/players", playerRequest, Map.class);

        assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(createResponse.getBody()).isNotNull();

        this.playerId = ((Number) createResponse.getBody().get("id")).longValue();
    }

    @When("I request the player by ID")
    public void requestPlayerById() {
        this.response = restTemplate.getForEntity("/players/" + this.playerId, Map.class);
    }

    @When("I update the position to {string}")
    public void updateThePositionTo(String newPosition) {
        this.playerRequest.put("position", newPosition);

        org.springframework.http.HttpEntity<java.util.Map<String, Object>> requestEntity =
                new org.springframework.http.HttpEntity<>(this.playerRequest);

        this.response = restTemplate.exchange(
                "/players/" + this.playerId,
                HttpMethod.PUT,
                requestEntity,
                java.util.Map.class
        );
    }

    @When("the order is deleted")
    public void whenOrderIsDeleted() {
        restTemplate.delete("/orders/" + playerId);
    }

    @When("I request an order with id {int}")
    public void whenRequestOrderById(Integer id) {
        response = restTemplate.getForEntity("/orders/{id}", String.class, id);
    }

    @When("I delete an order with id {int}")
    public void whenDeleteOrderById(Integer id) {
        restTemplate.delete("/orders/{id}", id);
        response = restTemplate.getForEntity("/orders/{id}", String.class, id);
    }

    @When("I request all orders")
    public void whenRequestAllOrders() {
        response = restTemplate.getForEntity("/orders", Object[].class);
    }

    @When("I create an order with invalid payload")
    public void whenCreateInvalidOrder() {
        response = restTemplate.postForEntity("/orders", "invalid-json", String.class);
    }

    // ---------- THEN ----------

    @Then("the player is persisted successfully")
    public void thenPlayerPersistedSuccessfully() {
        ResponseEntity<Map> getResponse =
                restTemplate.getForEntity("/players/" + playerId, Map.class);

        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(getResponse.getBody()).isNotNull();
        assertThat(((Number) getResponse.getBody().get("id")).longValue())
                .isEqualTo(playerId);
    }

    @Then("I should receive the player {string}")
    public void shouldReceivePlayer(String expectedName) {
        Map<String, Object> body = (Map<String, Object>) this.response.getBody();
        assertThat(body).isNotNull();
        assertThat(body.get("name")).isEqualTo(expectedName);
    }

    @Then("the player should now have the position {string}")
    public void thePlayerShouldNowHaveThePosition(String expectedPosition) {
        java.util.Map<String, Object> body = (Map<String, Object>) this.response.getBody();
        assertThat(body).isNotNull();
        assertThat(body.get("position")).isEqualTo(expectedPosition);
    }

    @Then("the order no longer exists")
    public void thenOrderNoLongerExists() {
        ResponseEntity<String> getResponse =
                restTemplate.getForEntity("/orders/" + playerId, String.class);

        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Then("the response status should be {int}")
    public void thenResponseStatusShouldBe(Integer status) {
        assertThat(response.getStatusCode().value()).isEqualTo(status);
    }
}
