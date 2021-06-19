package com.academic.academeet.controller;

import com.academic.academeet.domain.model.Carrer;
import com.academic.academeet.domain.model.Teacher;
import com.academic.academeet.domain.model.University;
import com.academic.academeet.domain.service.ICarrerService;
import com.academic.academeet.domain.service.ITeacherService;
import com.academic.academeet.domain.service.IUniversityService;
import com.academic.academeet.resource.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class TeacherController {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ICarrerService carrerService;
    @Autowired
    private ITeacherService teacherService;

    public Teacher convertToEntity(SaveTeacherResource resource){
        return modelMapper.map(resource,Teacher.class);
    }
    public TeacherResource convertToResource(Teacher entity){
        return modelMapper.map(entity, TeacherResource.class);
    }

    @PostMapping("/teachers")
    public TeacherResource createTeacher(@Valid @RequestBody SaveTeacherResource resource){
        Teacher teacher = convertToEntity(resource);
        return convertToResource(teacherService.createTeacher(teacher));
    }

    @GetMapping("/teachers/{teacherId}")
    public TeacherResource getTeacherById(@PathVariable Long teacherId){
        Teacher teacher = teacherService.getTeacherById(teacherId);
        return convertToResource(teacher);
    }

    @PutMapping("/teachers/{teacherId}")
    public TeacherResource updateTeacher(@PathVariable Long teacherId, @Valid @RequestBody SaveTeacherResource resource){
        Teacher teacher = convertToEntity(resource);
        return convertToResource(teacherService.updateTeacher(teacherId, teacher));
    }

    @DeleteMapping("/teachers/{teacherId}")
    public ResponseEntity<?> deleteTeacher(@PathVariable Long teacherId){
        teacherService.deleteTeacher(teacherId);
        return ResponseEntity.ok().build();
    }


}
