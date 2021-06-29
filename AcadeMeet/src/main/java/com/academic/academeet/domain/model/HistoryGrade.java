package com.academic.academeet.domain.model;

import javax.persistence.*;

@Entity
@Table(name = "history_grades")
public class HistoryGrade {
    @EmbeddedId
    private HistoryGradePK pk;

    private String score;


    public String getScore() {
        return score;
    }

    public HistoryGrade setScore(String score) {
        this.score = score;
        return this;
    }

    public HistoryGrade() {
    }

    public HistoryGradePK getPk() {
        return pk;
    }

    public HistoryGrade setPk(HistoryGradePK pk) {
        this.pk = pk;
        return this;
    }
}
