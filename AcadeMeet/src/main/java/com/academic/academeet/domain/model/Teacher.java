package com.academic.academeet.domain.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "teachers")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Size(max = 80)
    @NotNull
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            mappedBy = "teachers")
    private List<Carrer> carrers = new ArrayList<>();

    public Long getId() {
        return Id;
    }

    public Teacher setId(Long id) {
        Id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Teacher setName(String name) {
        this.name = name;
        return this;
    }

    public List<Carrer> getCarrers() {
        return carrers;
    }
}
