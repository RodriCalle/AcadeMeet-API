package com.academic.academeet.controller;

import com.academic.academeet.domain.model.LessonStudent;
import com.academic.academeet.domain.service.ILessonService;
import com.academic.academeet.domain.service.ILessonStudentService;
import com.academic.academeet.domain.service.IStudentService;
import com.academic.academeet.resource.LessonStudentResource;
import com.academic.academeet.resource.SaveLessonStudentResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LessonStudentController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private ILessonStudentService lessonStudentService;
    @Autowired
    private ILessonService lessonService;
    @Autowired
    private IStudentService studentService;

    private LessonStudent convertToEntity(SaveLessonStudentResource resource) {
        return mapper.map(resource, LessonStudent.class);
    }
    private LessonStudentResource convertToResource(LessonStudent entity) {
        return mapper.map(entity, LessonStudentResource.class);
    }

    @PostMapping("notifications/{notificationId}/lessons/{lessonId}/students/{studentId}")
    public LessonStudentResource createLessonStudent(@PathVariable Long notificationId,
                                                     @PathVariable Long lessonId,
                                                   @PathVariable Long studentId,
                                                   @RequestBody SaveLessonStudentResource resource) {
        LessonStudent lessonStudent = convertToEntity(resource);
        return convertToResource(lessonStudentService
                .createLessonStudent(notificationId, lessonId, studentId, lessonStudent));
    }

    @GetMapping("/lessons/{lessonId}/students/{studentId}")
    public LessonStudentResource getLessonStudentById(@PathVariable Long lessonId,
                                                      @PathVariable Long studentId) {
        return convertToResource(lessonStudentService.getLessonStudentById(lessonId, studentId));
    }

    @DeleteMapping("/lessons/{lessonId}/students/{studentId}")
    public ResponseEntity<?> deleteLessonStudent(@PathVariable Long lessonId,
                                                 @PathVariable Long studentId) {
        return lessonStudentService.deleteLessonStudent(lessonId,studentId);
    }
}
