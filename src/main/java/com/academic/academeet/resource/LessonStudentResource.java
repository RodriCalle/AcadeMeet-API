package com.academic.academeet.resource;

import com.academic.academeet.domain.model.LessonStudentPK;

public class LessonStudentResource {
    private LessonStudentPK pk;
    private String comment;
    private int qualification;
    private boolean assistence;

    public LessonStudentPK getPk() {
        return pk;
    }

    public LessonStudentResource setPk(LessonStudentPK pk) {
        this.pk = pk;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public LessonStudentResource setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public int getQualification() {
        return qualification;
    }

    public LessonStudentResource setQualification(int qualification) {
        this.qualification = qualification;
        return this;
    }

    public boolean isAssistence() {
        return assistence;
    }

    public LessonStudentResource setAssistence(boolean assistence) {
        this.assistence = assistence;
        return this;
    }
}
