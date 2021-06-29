package com.academic.academeet.resource;

import com.academic.academeet.domain.model.HistoryGradePK;

public class HistoryGradeResource {
    private HistoryGradePK pk;

    private String score;

    public HistoryGradePK getPk() {
        return pk;
    }

    public HistoryGradeResource setPk(HistoryGradePK pk) {
        this.pk = pk;
        return this;
    }

    public String getScore() {
        return score;
    }

    public HistoryGradeResource setScore(String score) {
        this.score = score;
        return this;
    }
}
