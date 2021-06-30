package com.academic.academeet.resource;

public class SaveLessonStudentResource {
    private String comment;
    private int qualification;
    private boolean assistence;

    public String getComment() {
        return comment;
    }

    public SaveLessonStudentResource setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public int getQualification() {
        return qualification;
    }

    public SaveLessonStudentResource setQualification(int qualification) {
        this.qualification = qualification;
        return this;
    }

    public boolean isAssistence() {
        return assistence;
    }

    public SaveLessonStudentResource setAssistence(boolean assistence) {
        this.assistence = assistence;
        return this;
    }
}
