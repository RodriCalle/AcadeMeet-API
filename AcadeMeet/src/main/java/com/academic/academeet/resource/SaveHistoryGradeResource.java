package com.academic.academeet.resource;

import com.academic.academeet.domain.model.HistoryGradePK;

public class SaveHistoryGradeResource {
    private String score;

    public String getScore() {
        return score;
    }

    public SaveHistoryGradeResource setScore(String score) {
        this.score = score;
        return this;
    }
}
