package com.academic.academeet.domain.service;

import com.academic.academeet.domain.model.LessonType;
import com.academic.academeet.domain.model.Schedule;
import com.academic.academeet.domain.model.TypeOfGrade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ITypeOfGradeService {
    Page<TypeOfGrade> getAllTypeOfGrades(Pageable pageable);
    TypeOfGrade getTypeOfGradeById(Long id);
    TypeOfGrade saveTypeOfGrade(TypeOfGrade typeOfGrade);
    TypeOfGrade updateTypeOfGrade(Long typeOfGradeId, TypeOfGrade typeOfGrade);
    ResponseEntity<?> deleteTypeOfGrade(Long id);
}
