package com.academic.academeet.domain.service;

import com.academic.academeet.domain.model.LessonType;
import com.academic.academeet.domain.model.Schedule;
import com.academic.academeet.domain.model.TypeOfGrade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ITypeOfGradeService {
    TypeOfGrade createTypeOfGrade(TypeOfGrade typeOfGrade);
    TypeOfGrade updateTypeOfGrade(Long typeOfGradeId, TypeOfGrade typeOfGradeDetails);
    TypeOfGrade getTypeOfGradeById(Long typeOfGradeId);
    ResponseEntity<?> deleteTypeOfGrade(Long typeOfGradeId);
    Page<TypeOfGrade> getAll(Pageable pageable);
}
