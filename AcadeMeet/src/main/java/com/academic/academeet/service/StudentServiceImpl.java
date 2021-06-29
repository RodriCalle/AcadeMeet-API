package com.academic.academeet.service;

import com.academic.academeet.domain.model.Student;
import com.academic.academeet.domain.repository.IStudentRepository;
import com.academic.academeet.domain.service.IStudentService;
import com.academic.academeet.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements IStudentService {

    @Autowired
    private IStudentRepository studentRepository;

    @Override
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student updateStudent(Long studentId, Student studentDetails) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(()-> new ResourceNotFoundException("Student", "Id", studentId));

        student.setFirst_name(studentDetails.getFirst_name());
        student.setLast_name(studentDetails.getLast_name());
        student.setMail(studentDetails.getMail());
        student.setPassword(studentDetails.getPassword());
        student.setBornDate(studentDetails.getBornDate());
        return studentRepository.save(student);
    }

    @Override
    public Student getStudentById(Long studentId) {
        return studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "Id", studentId));
    }

    @Override
    public ResponseEntity<?> deleteStudent(Long studentId) {
        return studentRepository.findById(studentId).
                map(student -> {
                    studentRepository.delete(student);
                    return ResponseEntity.ok().build(); })
                .orElseThrow(() -> new ResourceNotFoundException("Student", "Id", studentId));
    }

    @Override
    public Page<Student> getAll(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }
}
