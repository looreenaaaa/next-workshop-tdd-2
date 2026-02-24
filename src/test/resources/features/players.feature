Feature: Manage players
  In order to manage a football squad
  As an API user
  I want to create, read, update and delete players

  # CREATE
  Scenario: Create a player
    Given a player with name "Lamine Yamal", team "Barcelona", number 19 and position "Striker"
    When the player is saved
    Then the player is persisted successfully

  # READ
  Scenario: Get an existing player by ID
    Given an existing player with name "Vinicius", team "Real Madrid", number 7 and position "Forward"
    When I request the player by ID
    Then I should receive the player "Vinicius"
    And the response status should be 200

  # UPDATE
  Scenario: Update a player's position
    Given an existing player with name "Camavinga", team "Real Madrid", number 12 and position "Midfielder"
    When I update the position to "Left Back"
    Then the player should now have the position "Left Back"
    And the response status should be 200

  # DELETE
  Scenario: Delete an existing player
    Given an existing player with name "Gavi", team "Barcelona", number 6 and position "Midfielder"
    When the player is deleted
    Then the player no longer exists

  # ERROR CASES
  Scenario: Get non-existing player
    When I request a player with id 9999
    Then the response status should be 404
