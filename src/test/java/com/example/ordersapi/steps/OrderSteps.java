package com.example.ordersapi.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderSteps {

    @Autowired
    private TestRestTemplate restTemplate;

    private Map<String, Object> orderRequest;
    private Long orderId;
    private ResponseEntity<?> response;

    // ---------- GIVEN ----------

    @Given("an order with product {string} and quantity {int}")
    public void givenOrder(String product, int quantity) {
        orderRequest = new HashMap<>();
        orderRequest.put("product", product);
        orderRequest.put("quantity", quantity);
    }

    @Given("an existing order with product {string} and quantity {int}")
    public void givenExistingOrder(String product, int quantity) {
        Map<String, Object> request = new HashMap<>();
        request.put("product", product);
        request.put("quantity", quantity);

        ResponseEntity<Map> createResponse =
                restTemplate.postForEntity("/orders", request, Map.class);

        assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(createResponse.getBody()).isNotNull();

        this.orderId = ((Number) createResponse.getBody().get("id")).longValue();
    }

    // ---------- WHEN ----------

    @When("the order is saved")
    public void whenOrderIsSaved() {
        ResponseEntity<Map> createResponse =
                restTemplate.postForEntity("/orders", orderRequest, Map.class);

        assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(createResponse.getBody()).isNotNull();

        this.orderId = ((Number) createResponse.getBody().get("id")).longValue();
    }

    @When("the order is deleted")
    public void whenOrderIsDeleted() {
        restTemplate.delete("/orders/" + orderId);
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

    @Then("the order is persisted successfully")
    public void thenOrderPersistedSuccessfully() {
        ResponseEntity<Map> getResponse =
                restTemplate.getForEntity("/orders/" + orderId, Map.class);

        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(getResponse.getBody()).isNotNull();
        assertThat(((Number) getResponse.getBody().get("id")).longValue())
                .isEqualTo(orderId);
    }

    @Then("the order no longer exists")
    public void thenOrderNoLongerExists() {
        ResponseEntity<String> getResponse =
                restTemplate.getForEntity("/orders/" + orderId, String.class);

        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Then("the response status should be {int}")
    public void thenResponseStatusShouldBe(Integer status) {
        assertThat(response.getStatusCode().value()).isEqualTo(status);
    }
}
