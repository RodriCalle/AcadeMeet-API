package com.academic.academeet.domain.model;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class HistoryGradePK implements Serializable {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "type_of_grade_id", nullable = false)
    private TypeOfGrade typeOfGrade;

    public HistoryGradePK(Student student, Course course, TypeOfGrade typeOfGrade) {
        this.student = student;
        this.course = course;
        this.typeOfGrade = typeOfGrade;
    }

    public HistoryGradePK() {

    }
}
