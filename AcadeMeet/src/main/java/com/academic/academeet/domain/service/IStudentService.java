package com.academic.academeet.domain.service;

import com.academic.academeet.domain.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface IStudentService {
    Student createStudent(Student student);
    Student updateStudent(Long studentId, Student studentDetails);
    Student getStudentById(Long studentId);
    ResponseEntity<?> deleteStudent(Long studentId);
    Page<Student> getAll(Pageable pageable);
}
