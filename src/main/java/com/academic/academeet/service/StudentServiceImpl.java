package com.academic.academeet.service;

import com.academic.academeet.domain.model.Level;
import com.academic.academeet.domain.model.NotificationType;
import com.academic.academeet.domain.model.School;
import com.academic.academeet.domain.model.Student;
import com.academic.academeet.domain.repository.ILevelRepository;
import com.academic.academeet.domain.repository.ISchoolRepository;
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
    @Autowired
    private ILevelRepository levelRepository;
    @Autowired
    private ISchoolRepository schoolRepository;

    @Override
    public Student createStudent(Long levelId, Long schoolId, Student student) {
        return levelRepository.findById(levelId).map(level->{
            student.setLevel(level);
            return schoolRepository.findById(schoolId).map(school -> {
                student.setSchool(school);
                return studentRepository.save(student);
            }).orElseThrow(()->new ResourceNotFoundException("School", "Id", schoolId));
        }).orElseThrow(()->new ResourceNotFoundException("Level", "Id", levelId));
    }

    @Override
    public Student updateStudent(Long levelId, Long schoolId, Long studentId, Student studentDetails) {
        Level level = levelRepository.findById(levelId)
                .orElseThrow(()->new ResourceNotFoundException("Level", "Id",levelId));

        School school = schoolRepository.findById(schoolId)
                .orElseThrow(()->new ResourceNotFoundException("School", "Id", schoolId));

        return studentRepository.findById(studentId).map(student -> {
            student.setFirst_name(studentDetails.getFirst_name());
            student.setLast_name(studentDetails.getLast_name());
            student.setMail(studentDetails.getMail());
            student.setPassword(studentDetails.getPassword());
            student.setBornDate(studentDetails.getBornDate());
            student.setLevel(level);
            student.setSchool(school);
            return studentRepository.save(student);
        }).orElseThrow(() -> new ResourceNotFoundException("Student", "Id", studentId));
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
