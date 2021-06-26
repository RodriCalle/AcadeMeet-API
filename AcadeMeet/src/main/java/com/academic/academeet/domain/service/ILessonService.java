package com.academic.academeet.domain.service;

import com.academic.academeet.domain.model.Lesson;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ILessonService {
    Lesson createLesson (Long courseId, Long tutorId, Long lessonTypeId, Long scheduleId, Lesson lesson);
    Lesson updateLesson(Long lessonId, Lesson lessonDetails);
    Lesson getLessonById(Long lessonId);
    ResponseEntity<?> deleteLesson (Long lessonId);

    Page<Lesson> getAll(Pageable pageable);
}
