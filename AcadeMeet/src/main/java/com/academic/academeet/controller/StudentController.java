package com.academic.academeet.controller;

import com.academic.academeet.domain.model.Student;
import com.academic.academeet.domain.service.IStudentService;
import com.academic.academeet.resource.SaveStudentResource;
import com.academic.academeet.resource.StudentResource;
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
public class StudentController {

    @Autowired
    private IStudentService studentService;

    @Autowired
    private ModelMapper mapper;

    private Student convertToEntity(SaveStudentResource resource) {
        return mapper.map(resource, Student.class);
    }
    private StudentResource convertToResource(Student entity) {
        return mapper.map(entity, StudentResource.class);
    }

    @GetMapping("/students")
    public Page<StudentResource> getAllStudents(Pageable pageable){
        List<StudentResource> students = studentService.getAll(pageable)
                .getContent().stream().map(this::convertToResource)
                .collect(Collectors.toList());
        int studentsCount = students.size();
        return new PageImpl<>(students, pageable, studentsCount);
    }

    @GetMapping("/students/{id}")
    public StudentResource getStudentById(@PathVariable Long id) {
        return convertToResource(studentService.getStudentById(id));
    }

    @PostMapping("/students")
    public StudentResource createStudent(@RequestBody SaveStudentResource resource) {
        return convertToResource(studentService.createStudent(convertToEntity(resource)));
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
        return studentService.deleteStudent(id);
    }
}
