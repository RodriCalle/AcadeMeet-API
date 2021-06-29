package com.academic.academeet.domain.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private float average;

    /*public List<Student> getStudents() {
        return students;
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "HistoryGrades",
            joinColumns = {@JoinColumn(name = "course_id")},
            inverseJoinColumns = {@JoinColumn(name = "student_id")})
    private List<Student> students = new ArrayList<>();*/

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

    public float getAverage() {
        return average;
    }

    public void setAverage(float average) {
        this.average = average;
    }

    /*public boolean isTaggedWith(Student student) {
        return this.getStudents().contains(student);
    }

    public Course tagWith(Student student) {
        if (!this.isTaggedWith(student))
            this.getStudents().add(student);
        return this;
    }

    public Course untagWith(Student student) {
        if (!this.isTaggedWith(student))
            this.getStudents().remove(student);
        return this;
    }*/
}
