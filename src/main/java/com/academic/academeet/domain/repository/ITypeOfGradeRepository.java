package com.academic.academeet.domain.repository;

import com.academic.academeet.domain.model.TypeOfGrade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITypeOfGradeRepository extends JpaRepository<TypeOfGrade, Long> {
}
