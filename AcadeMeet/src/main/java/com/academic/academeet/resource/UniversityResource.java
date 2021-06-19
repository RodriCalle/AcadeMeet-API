package com.academic.academeet.resource;

public class UniversityResource {
    private Long Id;
    private String Name;

    public Long getId() {
        return Id;
    }

    public UniversityResource setId(Long id) {
        Id = id;
        return this;
    }

    public String getName() {
        return Name;
    }

    public UniversityResource setName(String name) {
        Name = name;
        return this;
    }
}
