package com.academic.academeet.domain.service;

import com.academic.academeet.domain.model.LessonType;
import com.academic.academeet.domain.model.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface LessonTypeService {
    Page<LessonType> getAllLessonTypes(Pageable pageable);
    LessonType getLessonTypeById(Long id);
    LessonType saveLessonType(LessonType lessonType);
    LessonType updateLessonType(Long lessonTypeId, LessonType lessonType);
    ResponseEntity<?> deleteLessonType(Long id);
}
