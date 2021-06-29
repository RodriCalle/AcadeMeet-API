package com.academic.academeet.controller;

import com.academic.academeet.domain.model.Carrer;
import com.academic.academeet.domain.model.HistoryGrade;
import com.academic.academeet.domain.model.HistoryGradePK;
import com.academic.academeet.domain.model.Lesson;
import com.academic.academeet.domain.service.*;
import com.academic.academeet.resource.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class HistoryGradeController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private IHistoryGradeService historyGradeService;
    @Autowired
    private ICourseService courseService;
    @Autowired
    private IStudentService studentService;
    @Autowired
    private ITypeOfGradeService typeOfGradeService;

    private HistoryGrade convertToEntity(SaveHistoryGradeResource resource) {
        return mapper.map(resource, HistoryGrade.class);
    }
    private HistoryGradeResource convertToResource(HistoryGrade entity) {
        return mapper.map(entity, HistoryGradeResource.class);
    }

    @PostMapping("/courses/{courseId}/students/{studentId}/types_of_grade/{typeOfGradeId}")
    public HistoryGradeResource createHistoryGrade(@PathVariable Long courseId,
                                                   @PathVariable Long studentId,
                                                   @PathVariable Long typeOfGradeId,
                                                   @RequestBody SaveHistoryGradeResource resource) {
        HistoryGrade hg = convertToEntity(resource);
        return convertToResource(historyGradeService
                .createHistoryGrade(courseId, studentId, typeOfGradeId, hg));
    }

    @GetMapping("/courses/{courseId}/students/{studentId}/types_of_grade/{typeOfGradeId}")
    public HistoryGradeResource getHistoryGradeById(@PathVariable Long courseId,
                                                    @PathVariable Long studentId,
                                                    @PathVariable Long typeOfGradeId) {
        return convertToResource(historyGradeService.getHistoryGradeById(courseId, studentId, typeOfGradeId));
    }


    @DeleteMapping("/courses/{courseId}/students/{studentId}/types_of_grade/{typeOfGradeId}")
    public ResponseEntity<?> deleteHistoryGrade(@PathVariable Long courseId,
                                             @PathVariable Long studentId,
                                             @PathVariable Long typeOfGradeId) {
        return historyGradeService.deleteHistoryGrade(courseId, studentId, typeOfGradeId);
    }
}
