package com.academic.academeet.resource;

public class TeacherResource {
    private Long Id;
    private String name;

    public Long getId() {
        return Id;
    }

    public TeacherResource setId(Long id) {
        Id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public TeacherResource setName(String name) {
        this.name = name;
        return this;
    }
}
