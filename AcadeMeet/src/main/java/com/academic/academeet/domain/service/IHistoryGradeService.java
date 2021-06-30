package com.academic.academeet.domain.service;

import com.academic.academeet.domain.model.HistoryGrade;
import com.academic.academeet.domain.model.Lesson;
import org.springframework.http.ResponseEntity;

public interface IHistoryGradeService {
    HistoryGrade createHistoryGrade(Long courseId,Long studentId, Long typeOfGradeId, HistoryGrade historyGrade);
    HistoryGrade getHistoryGradeById(Long courseId, Long studentId, Long typeOfGradeId);
    ResponseEntity<?> deleteHistoryGrade(Long courseId, Long studentId, Long typeOfGradeId);
}
