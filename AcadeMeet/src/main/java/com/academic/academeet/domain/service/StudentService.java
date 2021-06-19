package com.academic.academeet.domain.service;

import com.academic.academeet.domain.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface StudentService {
    Page<Student> getAllStudents(Pageable pageable);
    Student getStudentById(Long id);
    Student saveStudent(Student student);
    ResponseEntity<?> deleteStudent(Long id);
}
