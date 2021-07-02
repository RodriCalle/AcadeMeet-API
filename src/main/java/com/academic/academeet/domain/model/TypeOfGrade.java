package com.academic.academeet.domain.model;

import javax.persistence.*;

@Entity
@Table(name = "types_of_grade")
public class TypeOfGrade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    //@OneToMany(fetch = FetchType.LAZY, optional = false)
    //private List<HistoryGrades> historyGrades;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
