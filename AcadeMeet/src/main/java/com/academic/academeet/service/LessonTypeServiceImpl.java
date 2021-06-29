package com.academic.academeet.service;

import com.academic.academeet.domain.model.LessonType;
import com.academic.academeet.domain.repository.ILessonTypeRepository;
import com.academic.academeet.domain.service.ILessonTypeService;
import com.academic.academeet.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class LessonTypeServiceImpl implements ILessonTypeService {
    @Autowired
    private ILessonTypeRepository lessonTypeRepository;

    @Override
    public Page<LessonType> getAll(Pageable pageable) {
        return lessonTypeRepository.findAll(pageable);
    }

    @Override
    public LessonType getLessonTypeById(Long id) {
        return lessonTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("LessonType", "Id", id));
    }

    @Override
    public LessonType createLessonType(LessonType lessonType) {
        return lessonTypeRepository.save(lessonType);
    }

    @Override
    public LessonType updateLessonType(Long lessonTypeId, LessonType lessonTypeDetails) {
        return lessonTypeRepository.findById(lessonTypeId).map(
                LessonType1 ->{
                    LessonType1.setName(lessonTypeDetails.getName());
                    LessonType1.setDescription(lessonTypeDetails.getDescription());
                    LessonType1.setStudents_quantity(lessonTypeDetails.getStudents_quantity());
                    return lessonTypeRepository.save(LessonType1);
                }
        ).orElseThrow(() -> new ResourceNotFoundException("LessonType", "Id", lessonTypeId));
    }

    @Override
    public ResponseEntity<?> deleteLessonType(Long id) {
        return lessonTypeRepository.findById(id).
                map(student -> {
                    lessonTypeRepository.delete(student);
                    return ResponseEntity.ok().build(); })
                .orElseThrow(() -> new ResourceNotFoundException("LessonType", "Id", id));
    }
}
