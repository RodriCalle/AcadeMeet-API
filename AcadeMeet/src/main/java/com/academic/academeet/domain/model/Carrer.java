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

    //Relacion muchos carreras a una universidad el FK university_id se crea en carrers
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "university_id", nullable = false)
    @JsonIgnore
    private University university;

    public List<Teacher> getTeachers() {
        return teachers;
    }


    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "teacher_carrers",
            joinColumns = {@JoinColumn(name = "teacher_id")},
            inverseJoinColumns = { @JoinColumn(name = "carrer_id")})
    private List<Teacher> teachers = new ArrayList<>();

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

    public boolean isTaggedWith(Teacher teacher) {
        return this.getTeachers().contains(teacher);
    }

    public Carrer tagWith(Teacher teacher) {
        if(!this.isTaggedWith(teacher))
            this.getTeachers().add(teacher);
        return this;
    }

    public Carrer untagWith(Teacher teacher) {
        if(!this.isTaggedWith(teacher))
            this.getTeachers().remove(teacher);
        return this;
    }

}
