package com.academic.academeet.service;

import com.academic.academeet.domain.model.*;
import com.academic.academeet.domain.repository.ICourseRepository;
import com.academic.academeet.domain.repository.IHistoryGradeRepository;
import com.academic.academeet.domain.repository.IStudentRepository;
import com.academic.academeet.domain.repository.ITypeOfGradeRepository;
import com.academic.academeet.domain.service.IHistoryGradeService;
import com.academic.academeet.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class HistoryGradeServiceImpl implements IHistoryGradeService {
    @Autowired
    private IHistoryGradeRepository historyGradeRepository;
    @Autowired
    private ICourseRepository courseRepository;
    @Autowired
    private IStudentRepository studentRepository;
    @Autowired
    private ITypeOfGradeRepository typeOfGradeRepository;

    @Override
    public HistoryGrade createHistoryGrade(Long courseId, Long studentId, Long typeOfGradeId, HistoryGrade historyGrade) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(()-> new ResourceNotFoundException("Course", "Id", courseId));
        Student student = studentRepository.findById(studentId)
                .orElseThrow(()-> new ResourceNotFoundException("Student", "Id", studentId));
        TypeOfGrade typeOfGrade = typeOfGradeRepository.findById(typeOfGradeId)
                .orElseThrow(()-> new ResourceNotFoundException("TypeOfGrade", "Id", typeOfGradeId));
        HistoryGradePK pk = new HistoryGradePK(student, course,typeOfGrade);
        historyGrade.setPk(pk);
        return historyGradeRepository.save(historyGrade);
    }

    @Override
    public HistoryGrade getHistoryGradeById(Long courseId, Long studentId, Long typeOfGradeId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(()-> new ResourceNotFoundException("Course", "Id", courseId));
        Student student = studentRepository.findById(studentId)
                .orElseThrow(()-> new ResourceNotFoundException("Student", "Id", studentId));
        TypeOfGrade typeOfGrade = typeOfGradeRepository.findById(typeOfGradeId)
                .orElseThrow(()-> new ResourceNotFoundException("TypeOfGrade", "Id", typeOfGradeId));
        HistoryGradePK pk = new HistoryGradePK(student, course,typeOfGrade);
        return historyGradeRepository.findById(pk)
                .orElseThrow(()-> new ResourceNotFoundException("HistoryGrade not found"));
    }

    @Override
    public ResponseEntity<?> deleteHistoryGrade(Long courseId, Long studentId, Long typeOfGradeId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(()-> new ResourceNotFoundException("Course", "Id", courseId));
        Student student = studentRepository.findById(studentId)
                .orElseThrow(()-> new ResourceNotFoundException("Student", "Id", studentId));
        TypeOfGrade typeOfGrade = typeOfGradeRepository.findById(typeOfGradeId)
                .orElseThrow(()-> new ResourceNotFoundException("TypeOfGrade", "Id", typeOfGradeId));

        HistoryGradePK pk = new HistoryGradePK(student, course, typeOfGrade);
        HistoryGrade historyGrade = historyGradeRepository.findById(pk)
                .orElseThrow(()-> new ResourceNotFoundException("There was a error deleting HistoryGrade"));
        historyGradeRepository.delete(historyGrade);
        return ResponseEntity.ok().build();
    }
}
