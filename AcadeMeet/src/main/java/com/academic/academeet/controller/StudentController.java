package com.academic.academeet.controller;

import com.academic.academeet.domain.model.Student;
import com.academic.academeet.domain.repository.ILevelRepository;
import com.academic.academeet.domain.repository.ISchoolRepository;
import com.academic.academeet.domain.service.ILevelService;
import com.academic.academeet.domain.service.ISchoolService;
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
    private ILevelService levelService;
    @Autowired
    private ISchoolService schoolService;

    @Autowired
    private ModelMapper mapper;

    private Student convertToEntity(SaveStudentResource resource) {
        return mapper.map(resource, Student.class);
    }
    private StudentResource convertToResource(Student entity) {
        return mapper.map(entity, StudentResource.class);
    }

    @PostMapping("/levels/{levelId}/schools/{schoolId}/students")
    public StudentResource createStudent(@PathVariable Long levelId,
                                         @PathVariable Long schoolId,
                                         @RequestBody SaveStudentResource resource) {
        return convertToResource(studentService.createStudent(levelId,schoolId,convertToEntity(resource)));
    }

    @PutMapping("/levels/{levelId}/schools/{schoolId}/students/{studentId}")
    public StudentResource createStudent(@PathVariable Long levelId,
                                         @PathVariable Long schoolId,
                                         @PathVariable Long studentId,
                                         @RequestBody SaveStudentResource resource) {
        return convertToResource(studentService.updateStudent(levelId,schoolId,studentId,convertToEntity(resource)));
    }

    @GetMapping("/students/{studentId}")
    public StudentResource getStudentById(@PathVariable Long studentId) {
        return convertToResource(studentService.getStudentById(studentId));
    }

    @DeleteMapping("/students/{studentId}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long studentId) {
        return studentService.deleteStudent(studentId);
    }

    @GetMapping("/students")
    public Page<StudentResource> getAllStudents(Pageable pageable){
        List<StudentResource> students = studentService.getAll(pageable)
                .getContent().stream().map(this::convertToResource)
                .collect(Collectors.toList());
        int studentsCount = students.size();
        return new PageImpl<>(students, pageable, studentsCount);
    }
}
