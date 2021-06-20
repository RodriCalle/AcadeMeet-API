package com.academic.academeet.domain.repository;

import com.academic.academeet.domain.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
