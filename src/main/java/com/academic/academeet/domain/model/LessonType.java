package com.academic.academeet.domain.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "lesson_types")
public class LessonType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private int students_quantity;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStudents_quantity() {
        return students_quantity;
    }

    public void setStudents_quantity(int students_quantity) {
        this.students_quantity = students_quantity;
    }
}
