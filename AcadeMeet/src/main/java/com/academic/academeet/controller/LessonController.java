package com.academic.academeet.controller;

import com.academic.academeet.domain.model.Lesson;
import com.academic.academeet.domain.model.Tutor;
import com.academic.academeet.domain.service.*;
import com.academic.academeet.resource.*;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class LessonController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private ILessonService lessonService;
    @Autowired
    private ICourseService courseService;
    @Autowired
    private ITutorService tutorService;
    @Autowired
    private ILessonTypeService ILessonTypeService;
    @Autowired
    private IScheduleService IScheduleService;

    private Lesson convertToEntity(SaveLessonResource resource) {
        return mapper.map(resource, Lesson.class);
    }
    private LessonResource convertToResource(Lesson entity) {
        return mapper.map(entity, LessonResource.class);
    }

    @Operation(summary = "Add Lesson"
            , description = "Add Lesson By Id"
            , tags = {"lessons"})
    @PostMapping("/lesson_types/{lessonTypeId}/courses/{courseId}/tutors/{tutorId}/schedules/{scheduleId}/lessons")
    public LessonResource createLesson(@PathVariable Long lessonTypeId,
                                       @PathVariable Long courseId,
                                       @PathVariable Long tutorId,
                                       @PathVariable Long scheduleId,
                                       @RequestBody SaveLessonResource resource) {
        Lesson lesson = convertToEntity(resource);
        return convertToResource(lessonService.createLesson(courseId, tutorId,
                lessonTypeId, scheduleId, lesson));
    }

    @Operation(summary = "Get Lesson"
            , description = "Get Lesson By Id"
            , tags = {"lessons"})
    @GetMapping("/lessons/{lessonId}")
    public LessonResource getLessonById(@PathVariable Long lessonId){
        Lesson lesson = lessonService.getLessonById(lessonId);
        return convertToResource(lesson);
    }

    @Operation(summary = "Update Lesson"
            , description = "Update Lesson By Id"
            , tags = {"lessons"})
    @PutMapping("/lessons/{lessonId}")
    public LessonResource updateLesson(@PathVariable Long lessonId, @Valid @RequestBody SaveLessonResource resource){
        Lesson lesson = convertToEntity(resource);
        return convertToResource(lessonService.updateLesson(lessonId, lesson));
    }

    @Operation(summary = "Delete Lesson"
            , description = "Delete Lesson By Id"
            , tags = {"lessons"})
    @DeleteMapping("/lessons/{lessonId}")
    public ResponseEntity<?> deleteLesson(@PathVariable Long lessonId){
        lessonService.deleteLesson(lessonId);
        return ResponseEntity.ok().build();
    }

}
