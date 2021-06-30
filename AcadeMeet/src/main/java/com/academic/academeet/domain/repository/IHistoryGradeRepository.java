package com.academic.academeet.domain.repository;

import com.academic.academeet.domain.model.HistoryGrade;
import com.academic.academeet.domain.model.HistoryGradePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

public interface IHistoryGradeRepository extends JpaRepository<HistoryGrade, HistoryGradePK> {
}
