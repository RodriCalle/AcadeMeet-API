package com.academic.academeet.service;

import com.academic.academeet.domain.model.*;
import com.academic.academeet.domain.repository.*;
import com.academic.academeet.domain.service.ILessonStudentService;
import com.academic.academeet.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class LessonStudentServiceImpl implements ILessonStudentService {
    @Autowired
    private ILessonStudentRepository lessonStudentRepository;
    @Autowired
    private ILessonRepository lessonRepository;
    @Autowired
    private IStudentRepository studentRepository;
    @Autowired
    private INotificationRepository notificationRepository;

    @Override
    public LessonStudent createLessonStudent(Long notificationId, Long lessonId, Long studentId, LessonStudent lessonStudent) {
        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(()-> new ResourceNotFoundException("Lesson", "Id", lessonId));
        Student student = studentRepository.findById(studentId)
                .orElseThrow(()-> new ResourceNotFoundException("Student", "Id", studentId));
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(()-> new ResourceNotFoundException("Notification", "Id", notificationId));
        LessonStudentPK pk = new LessonStudentPK(student, lesson);
        lessonStudent.setPk(pk);
        lessonStudent.setNotification(notification);
        return lessonStudentRepository.save(lessonStudent);
    }

    @Override
    public LessonStudent getLessonStudentById(Long lessonId, Long studentId) {
        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(()-> new ResourceNotFoundException("Lesson", "Id", lessonId));
        Student student = studentRepository.findById(studentId)
                .orElseThrow(()-> new ResourceNotFoundException("Student", "Id", studentId));
        LessonStudentPK pk = new LessonStudentPK(student, lesson);
        return lessonStudentRepository.findById(pk)
                .orElseThrow(()-> new ResourceNotFoundException("LessonStudent not found"));
    }

    @Override
    public ResponseEntity<?> deleteLessonStudent(Long lessonId, Long studentId) {
        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(()-> new ResourceNotFoundException("Lesson", "Id", lessonId));
        Student student = studentRepository.findById(studentId)
                .orElseThrow(()-> new ResourceNotFoundException("Student", "Id", studentId));
        LessonStudentPK pk = new LessonStudentPK(student, lesson);
        LessonStudent lessonStudent = lessonStudentRepository.findById(pk)
                .orElseThrow(()-> new ResourceNotFoundException("There was a error deleting LessonStudent"));
        lessonStudentRepository.delete(lessonStudent);
        return ResponseEntity.ok().build();
    }
}
