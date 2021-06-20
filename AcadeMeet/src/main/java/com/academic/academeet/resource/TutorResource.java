package com.academic.academeet.resource;

public class TutorResource {
    private Long Id;
    private String name;

    public Long getId() {
        return Id;
    }

    public TutorResource setId(Long id) {
        Id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public TutorResource setName(String name) {
        this.name = name;
        return this;
    }
}
