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

    //Relacion una universidad a muchos carreras el FK university_id se crea en carrers
    /*@OneToMany(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
    @JoinColumn(name = "university_id", nullable = false)
    private List<Carrer> carrers;*/

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
