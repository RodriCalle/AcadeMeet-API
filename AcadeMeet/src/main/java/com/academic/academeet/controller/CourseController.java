package com.academic.academeet.controller;

import com.academic.academeet.domain.model.Course;
import com.academic.academeet.domain.model.User;
import com.academic.academeet.domain.service.ICourseService;
import com.academic.academeet.domain.service.StudentService;
import com.academic.academeet.resource.CourseResource;
import com.academic.academeet.resource.SaveCourseResource;
import com.academic.academeet.resource.SaveUserResource;
import com.academic.academeet.resource.UserResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CourseController {

    @Autowired
    private ICourseService courseService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping("/courses")
    public List<CourseResource> getAllCourses() {
        return courseService.getAllCourses()
                .stream()
                .map(
                        course -> mapper.map(course, CourseResource.class)
                ).collect(Collectors.toList());
    }

    @PostMapping("/courses")
    public CourseResource saveCourse(@RequestBody SaveCourseResource resource) {
        Course course = convertToEntity(resource);
        return convertToResource(courseService.createCourse(course));
    }

    @PutMapping("/courses/{id}")
    public CourseResource updateCourse(@PathVariable Long id, @RequestBody SaveCourseResource resource) {
        Course course = convertToEntity(resource);
        return convertToResource(courseService.updateCourse(id,course));
    }

    @DeleteMapping("/courses/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) { return courseService.deleteCourse(id); }

    @PostMapping("courses/{courseid}/students/{studentid}")
    public CourseResource assignStudentCourse(@PathVariable Long courseid, @PathVariable Long studentid){
        return convertToResource(courseService.assignStudentCourse(courseid,studentid));
    }

    //TODO: Fix unassign student Course

    @DeleteMapping("courses/{courseid}/students/{studentid}")
    public CourseResource unassignStudentCourse(@PathVariable Long courseid, @PathVariable Long studentid){
        return convertToResource(courseService.unassignStudentCourse(courseid,studentid));
    }

    private Course convertToEntity(SaveCourseResource resource) {
        return mapper.map(resource, Course.class);
    }

    private CourseResource convertToResource(Course entity) {
        return mapper.map(entity, CourseResource.class);
    }
}
