package com.academic.academeet.service;

import com.academic.academeet.domain.model.TypeOfGrade;
import com.academic.academeet.domain.repository.TypeOfGradeRepository;
import com.academic.academeet.domain.service.TypeOfGradeService;
import com.academic.academeet.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TypeOfGradeServiceImpl implements TypeOfGradeService {
    @Autowired
    private TypeOfGradeRepository typeOfGradeRepository;

    @Override
    public Page<TypeOfGrade> getAllTypeOfGrades(Pageable pageable) {
        return typeOfGradeRepository.findAll(pageable);
    }

    @Override
    public TypeOfGrade getTypeOfGradeById(Long id) {
        return typeOfGradeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TypeOfGrade", "Id", id));
    }

    @Override
    public TypeOfGrade saveTypeOfGrade(TypeOfGrade typeOfGrade) {
        return typeOfGradeRepository.save(typeOfGrade);
    }

    @Override
    public TypeOfGrade updateTypeOfGrade(Long typeOfGradeId, TypeOfGrade typeOfGrade) {
        return typeOfGradeRepository.findById(typeOfGradeId).map(
                TypeOfGrade1 ->{
                    TypeOfGrade1.setName(typeOfGrade.getName());
                    return typeOfGradeRepository.save(TypeOfGrade1);
                }
        ).orElseThrow(() -> new ResourceNotFoundException("TypeOfGrade", "Id", typeOfGrade));
    }

    @Override
    public ResponseEntity<?> deleteTypeOfGrade(Long id) {
        return typeOfGradeRepository.findById(id).
                map(student -> {
                    typeOfGradeRepository.delete(student);
                    return ResponseEntity.ok().build(); })
                .orElseThrow(() -> new ResourceNotFoundException("TypeOfGrade", "Id", id));
    }
}
