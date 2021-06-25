package com.academic.academeet.service;

import com.academic.academeet.domain.model.LessonType;
import com.academic.academeet.domain.repository.LessonTypeRepository;
import com.academic.academeet.domain.service.LessonTypeService;
import com.academic.academeet.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class LessonTypeServiceImpl implements LessonTypeService {
    @Autowired
    private LessonTypeRepository lessonTypeRepository;

    @Override
    public Page<LessonType> getAllLessonTypes(Pageable pageable) {
        return lessonTypeRepository.findAll(pageable);
    }

    @Override
    public LessonType getLessonTypeById(Long id) {
        return lessonTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("LessonType", "Id", id));
    }

    @Override
    public LessonType saveLessonType(LessonType lessonType) {
        return lessonTypeRepository.save(lessonType);
    }

    @Override
    public LessonType updateLessonType(Long lessonTypeId, LessonType lessonType) {
        return lessonTypeRepository.findById(lessonTypeId).map(
                LessonType1 ->{
                    LessonType1.setName(lessonType.getName());
                    LessonType1.setDescription(lessonType.getDescription());
                    LessonType1.setStudents_quantity(lessonType.getStudents_quantity());
                    return lessonTypeRepository.save(LessonType1);
                }
        ).orElseThrow(() -> new ResourceNotFoundException("LessonType", "Id", lessonType));
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
