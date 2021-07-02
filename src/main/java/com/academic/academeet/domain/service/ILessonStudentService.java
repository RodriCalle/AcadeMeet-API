package com.academic.academeet.domain.service;

import com.academic.academeet.domain.model.LessonStudent;
import org.springframework.http.ResponseEntity;

public interface ILessonStudentService {
    LessonStudent createLessonStudent(Long notificationId, Long lessonId, Long studentId, LessonStudent lessonStudent);
    LessonStudent getLessonStudentById(Long lessonId, Long studentId);
    ResponseEntity<?> deleteLessonStudent(Long lessonId, Long studentId);
}
