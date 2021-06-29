package com.academic.academeet.domain.service;

import com.academic.academeet.domain.model.LessonType;
import com.academic.academeet.domain.model.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ILessonTypeService {
    LessonType createLessonType(LessonType lessonType);
    LessonType updateLessonType(Long lessonTypeId, LessonType lessonTypeDetails);
    LessonType getLessonTypeById(Long id);
    ResponseEntity<?> deleteLessonType(Long id);
    Page<LessonType> getAll(Pageable pageable);
}
