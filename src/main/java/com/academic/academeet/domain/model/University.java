package com.academic.academeet.domain.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "universities")
public class University {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Size(min = 10,max = 80)
    @NotNull
    private String Name;

    public University() {
    }

    public Long getId() {
        return Id;
    }

    public University setId(Long id) {
        Id = id;
        return this;
    }

    public String getName() {
        return Name;
    }

    public University setName(String name) {
        Name = name;
        return this;
    }
}
