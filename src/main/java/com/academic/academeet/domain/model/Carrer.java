package com.academic.academeet.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carrers")
public class Carrer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @NotNull
    @Size(max = 150)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "university_id", nullable = false)
    @JsonIgnore
    private University university;

    public List<Tutor> getTutors() {
        return tutors;
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "tutor_carrers",
            joinColumns = {@JoinColumn(name = "carrer_id")},
            inverseJoinColumns = { @JoinColumn(name = "tutor_id")})
    private List<Tutor> tutors = new ArrayList<>();

    public Carrer() {
    }

    public Long getId() {
        return Id;
    }

    public Carrer setId(Long id) {
        Id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Carrer setName(String name) {
        this.name = name;
        return this;
    }

    public University getUniversity() {
        return university;
    }

    public Carrer setUniversity(University university) {
        this.university = university;
        return this;
    }

    public boolean isTaggedWith(Tutor tutor) {
        return this.getTutors().contains(tutor);
    }

    public Carrer tagWith(Tutor tutor) {
        if(!this.isTaggedWith(tutor))
            this.getTutors().add(tutor);
        return this;
    }

    public Carrer untagWith(Tutor tutor) {
            this.getTutors().remove(tutor);
        return this;
    }

}
