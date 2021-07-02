package com.academic.academeet.resource;

public class LevelResource {
    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public LevelResource setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public LevelResource setName(String name) {
        this.name = name;
        return this;
    }
}
