package com.academic.academeet.domain.repository;

import com.academic.academeet.domain.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITeacherRepository extends JpaRepository<Teacher, Long> {
}
