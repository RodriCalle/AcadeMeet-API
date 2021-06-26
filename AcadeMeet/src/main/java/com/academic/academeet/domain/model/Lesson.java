package com.academic.academeet.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "lessons")
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String topic;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tutor_id", nullable = false)
    @JsonIgnore
    private Tutor tutor;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "course_id", nullable = false)
    @JsonIgnore
    private Course course;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "lesson_type_id", nullable = false)
    @JsonIgnore
    private LessonType lesson_type;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "schedule_id", nullable = false)
    @JsonIgnore
    private Schedule schedule;

    public Long getId() {
        return id;
    }

    public Lesson setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTopic() {
        return topic;
    }

    public Lesson setTopic(String topic) {
        this.topic = topic;
        return this;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public Lesson setTutor(Tutor tutor) {
        this.tutor = tutor;
        return this;
    }

    public Course getCourse() {
        return course;
    }

    public Lesson setCourse(Course course) {
        this.course = course;
        return this;
    }

    public LessonType getLesson_type() {
        return lesson_type;
    }

    public Lesson setLesson_type(LessonType lesson_type) {
        this.lesson_type = lesson_type;
        return this;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public Lesson setSchedule(Schedule schedule) {
        this.schedule = schedule;
        return this;
    }
}
