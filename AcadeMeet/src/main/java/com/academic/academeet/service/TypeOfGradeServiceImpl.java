package com.academic.academeet.service;

import com.academic.academeet.domain.model.TypeOfGrade;
import com.academic.academeet.domain.repository.ITypeOfGradeRepository;
import com.academic.academeet.domain.service.ITypeOfGradeService;
import com.academic.academeet.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TypeOfGradeServiceImpl implements ITypeOfGradeService {
    @Autowired
    private ITypeOfGradeRepository typeOfGradeRepository;

    @Override
    public Page<TypeOfGrade> getAll(Pageable pageable) {
        return typeOfGradeRepository.findAll(pageable);
    }

    @Override
    public TypeOfGrade getTypeOfGradeById(Long typeOfGradeId) {
        return typeOfGradeRepository.findById(typeOfGradeId)
                .orElseThrow(() -> new ResourceNotFoundException("TypeOfGrade", "Id", typeOfGradeId));
    }

    @Override
    public TypeOfGrade createTypeOfGrade(TypeOfGrade typeOfGrade) {
        return typeOfGradeRepository.save(typeOfGrade);
    }

    @Override
    public TypeOfGrade updateTypeOfGrade(Long typeOfGradeId, TypeOfGrade typeOfGradeDetails) {
        return typeOfGradeRepository.findById(typeOfGradeId).map(
                TypeOfGrade1 ->{
                    TypeOfGrade1.setName(typeOfGradeDetails.getName());
                    return typeOfGradeRepository.save(TypeOfGrade1);
                }
        ).orElseThrow(() -> new ResourceNotFoundException("TypeOfGrade", "Id", typeOfGradeDetails));
    }

    @Override
    public ResponseEntity<?> deleteTypeOfGrade(Long typeOfGradeId) {
        return typeOfGradeRepository.findById(typeOfGradeId).
                map(student -> {
                    typeOfGradeRepository.delete(student);
                    return ResponseEntity.ok().build(); })
                .orElseThrow(() -> new ResourceNotFoundException("TypeOfGrade", "Id", typeOfGradeId));
    }
}
