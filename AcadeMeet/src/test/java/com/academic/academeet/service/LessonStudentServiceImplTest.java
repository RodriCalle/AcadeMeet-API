package com.academic.academeet.service;

import com.academic.academeet.domain.model.*;
import com.academic.academeet.domain.repository.*;
import com.academic.academeet.domain.service.ILessonStudentService;
import com.academic.academeet.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class LessonStudentServiceImplTest {
    @Autowired
    private ILessonStudentService lessonStudentService;

    @MockBean
    private ILessonStudentRepository lessonStudentRepository;
    @MockBean
    private ILessonRepository lessonRepository;
    @MockBean
    private IStudentRepository studentRepository;
    @MockBean
    private INotificationRepository notificationRepository;

    @TestConfiguration
    static class LessonStudentServiceTestConfiguration{
        @Bean
        public ILessonStudentService userService() {
            return new LessonStudentServiceImpl();
        }
    }

    @Test
    public void whenCreateLessonStudentReturnsLessonStudent(){
        Long lessonId = 1L;Long studentId = 1L;Long notificationId = 1L;
        Lesson lesson = new Lesson(); lesson.setId(lessonId);
        Student student = new Student(); student.setId(studentId);
        Notification notification = new Notification(); notification.setId(notificationId);
        LessonStudent lessonStudent = new LessonStudent();
        lessonStudent.setPk(new LessonStudentPK(student,lesson));
        lessonStudent.setNotification(notification); lessonStudent.setAssistence(true);
        lessonStudent.setQualification(8); lessonStudent.setComment("Buena clase");

        when(lessonRepository.findById(lessonId)).thenReturn(Optional.of(lesson));
        when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));
        when(notificationRepository.findById(notificationId)).thenReturn(Optional.of(notification));
        when(lessonStudentRepository.save(lessonStudent)).thenReturn(lessonStudent);

        LessonStudent result =
                lessonStudentService.createLessonStudent(notificationId,lessonId,studentId, lessonStudent);

        assertThat(result).isEqualTo(lessonStudent);
    }

    @Test
    public void whenCreateLessonStudentReturnsLessonException(){
        Long lessonId = 1L;Long studentId = 1L;Long notificationId = 1L;
        Lesson lesson = new Lesson(); lesson.setId(lessonId);
        Student student = new Student(); student.setId(studentId);
        Notification notification = new Notification(); notification.setId(notificationId);
        LessonStudent lessonStudent = new LessonStudent();
        lessonStudent.setPk(new LessonStudentPK(student,lesson));
        lessonStudent.setNotification(notification); lessonStudent.setAssistence(true);
        lessonStudent.setQualification(8); lessonStudent.setComment("Buena clase");

        when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));
        when(notificationRepository.findById(notificationId)).thenReturn(Optional.of(notification));
        when(lessonStudentRepository.save(lessonStudent)).thenReturn(lessonStudent);

        Throwable exception = catchThrowable(() -> {
            LessonStudent result =
                    lessonStudentService.createLessonStudent(notificationId,lessonId,studentId, lessonStudent);
        });

        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Resource Lesson not found for Id with value 1");

    }

    @Test
    public void whenCreateLessonStudentReturnsStudentException(){
        Long lessonId = 1L;Long studentId = 1L;Long notificationId = 1L;
        Lesson lesson = new Lesson(); lesson.setId(lessonId);
        Student student = new Student(); student.setId(studentId);
        Notification notification = new Notification(); notification.setId(notificationId);
        LessonStudent lessonStudent = new LessonStudent();
        lessonStudent.setPk(new LessonStudentPK(student,lesson));
        lessonStudent.setNotification(notification); lessonStudent.setAssistence(true);
        lessonStudent.setQualification(8); lessonStudent.setComment("Buena clase");

        when(lessonRepository.findById(lessonId)).thenReturn(Optional.of(lesson));
        when(notificationRepository.findById(notificationId)).thenReturn(Optional.of(notification));
        when(lessonStudentRepository.save(lessonStudent)).thenReturn(lessonStudent);

        Throwable exception = catchThrowable(() -> {
            LessonStudent result =
                    lessonStudentService.createLessonStudent(notificationId,lessonId,studentId, lessonStudent);
        });

        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Resource Student not found for Id with value 1");

    }

}
