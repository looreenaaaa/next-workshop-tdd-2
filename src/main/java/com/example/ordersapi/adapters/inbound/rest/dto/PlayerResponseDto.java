package com.example.ordersapi.adapters.inbound.rest.dto;

public class PlayerResponseDto {

    private Long id;
    private String name;
    private String team;
    private int number;
    private String position;

    public PlayerResponseDto(Long id, String name, String team, int number, String position) {
        this.id = id;
        this.name = name;
        this.team = team;
        this.number = number;
        this.position = position;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) { this.id = id;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
