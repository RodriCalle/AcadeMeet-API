package com.academic.academeet.domain.service;

import com.academic.academeet.domain.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface StudentService {
    Page<Student> getAllStudents(Pageable pageable);
    Student getStudentById(Long id);
    Student createStudent(Student student);
    Student updateStudent(Long studentId, Student studentDetails);
    ResponseEntity<?> deleteStudent(Long id);
}
