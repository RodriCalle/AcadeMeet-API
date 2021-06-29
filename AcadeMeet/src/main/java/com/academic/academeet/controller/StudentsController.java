package com.academic.academeet.controller;

import com.academic.academeet.domain.model.Student;
import com.academic.academeet.domain.service.StudentService;
import com.academic.academeet.resource.SaveStudentResource;
import com.academic.academeet.resource.StudentResource;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class StudentsController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private ModelMapper mapper;

    @Operation(summary = "Get All Students"
            , description = "Get All Students"
            , tags = {"students"})
    @GetMapping("/students")
    public Page<StudentResource> getAllStudents(Pageable pageable){
        List<StudentResource> students = studentService.getAllStudents(pageable)
                .getContent().stream().map(this::convertToResource)
                .collect(Collectors.toList());
        int studentsCount = students.size();
        return new PageImpl<>(students, pageable, studentsCount);
    }

    @Operation(summary = "Get Student"
            , description = "Get Student By Id"
            , tags = {"students"})
    @GetMapping("/students/{id}")
    public StudentResource getStudentById(@PathVariable Long id) {
        return convertToResource(studentService.getStudentById(id));
    }

    @Operation(summary = "Add Student"
            , description = "Add Student"
            , tags = {"students"})
    @PostMapping("/students")
    public StudentResource createStudent(SaveStudentResource resource) {
        return convertToResource(studentService.saveStudent(convertToEntity(resource)));
    }

    @Operation(summary = "Delete Student"
            , description = "Delete Student By Id"
            , tags = {"students"})
    @DeleteMapping("/students/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
        return studentService.deleteStudent(id);
    }

    private Student convertToEntity(SaveStudentResource resource) {
        return mapper.map(resource, Student.class);
    }
    private StudentResource convertToResource(Student entity) {
        return mapper.map(entity, StudentResource.class);
    }

}
