package com.academic.academeet.controller;

import com.academic.academeet.domain.model.Course;
import com.academic.academeet.domain.service.ICourseService;
import com.academic.academeet.resource.CourseResource;
import com.academic.academeet.resource.SaveCourseResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CourseController {
    @Autowired
    private ICourseService courseService;

    @Autowired
    private ModelMapper mapper;

    private Course convertToEntity(SaveCourseResource resource) {
        return mapper.map(resource, Course.class);
    }
    private CourseResource convertToResource(Course entity) {
        return mapper.map(entity, CourseResource.class);
    }

    @PostMapping("/courses")
    public CourseResource createCourse(@RequestBody SaveCourseResource resource) {
        Course course = convertToEntity(resource);
        return convertToResource(courseService.createCourse(course));
    }

    @PutMapping("/courses/{courseId}")
    public CourseResource updateCourse(@PathVariable Long courseId, @RequestBody SaveCourseResource resource) {
        Course course = convertToEntity(resource);
        return convertToResource(courseService.updateCourse(courseId,course));
    }

    @GetMapping("/courses/{courseId}")
    public CourseResource getCourseById(@PathVariable Long courseId) {
        Optional<Course> course = courseService.getCourseById(courseId);
        return course.map(this::convertToResource).orElse(null);
    }

    @DeleteMapping("/courses/{courseId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long courseId) { return courseService.deleteCourse(courseId); }

    @GetMapping("/courses")
    public List<CourseResource> getAll() {
        return courseService.getAll()
                .stream()
                .map(
                        course -> mapper.map(course, CourseResource.class)
                ).collect(Collectors.toList());
    }

    /*@PostMapping("courses/{courseid}/students/{studentid}")
    public CourseResource assignStudentCourse(@PathVariable Long courseid, @PathVariable Long studentid){
        return convertToResource(courseService.assignStudentCourse(courseid,studentid));
    }

    @DeleteMapping("courses/{courseid}/students/{studentid}")
    public CourseResource unassignStudentCourse(@PathVariable Long courseid, @PathVariable Long studentid){
        return convertToResource(courseService.unassignStudentCourse(courseid,studentid));
    }*/
}
