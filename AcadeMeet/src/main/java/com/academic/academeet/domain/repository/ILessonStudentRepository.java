package com.academic.academeet.domain.repository;

import com.academic.academeet.domain.model.LessonStudent;
import com.academic.academeet.domain.model.LessonStudentPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ILessonStudentRepository extends JpaRepository<LessonStudent, LessonStudentPK> {
}
