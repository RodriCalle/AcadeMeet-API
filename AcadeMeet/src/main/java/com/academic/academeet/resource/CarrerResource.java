package com.academic.academeet.resource;

public class CarrerResource {
    private Long Id;
    private String name;

    public Long getId() {
        return Id;
    }

    public CarrerResource setId(Long id) {
        Id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public CarrerResource setName(String name) {
        this.name = name;
        return this;
    }
}
