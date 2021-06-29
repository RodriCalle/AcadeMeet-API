package com.academic.academeet.domain.model;

import javax.persistence.*;

@Entity
@Table(name = "lesson_students")
public class LessonStudent {
    @EmbeddedId
    private LessonStudentPK pk;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "notification_id", nullable = false)
    private Notification notification;

    private String comment;
    private int qualification;
    private boolean assistence;

    public LessonStudentPK getPk() {
        return pk;
    }

    public LessonStudent setPk(LessonStudentPK pk) {
        this.pk = pk;
        return this;
    }

    public Notification getNotification() {
        return notification;
    }

    public LessonStudent setNotification(Notification notification) {
        this.notification = notification;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public LessonStudent setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public int getQualification() {
        return qualification;
    }

    public LessonStudent setQualification(int qualification) {
        this.qualification = qualification;
        return this;
    }

    public boolean isAssistence() {
        return assistence;
    }

    public LessonStudent setAssistence(boolean assistence) {
        this.assistence = assistence;
        return this;
    }
}
