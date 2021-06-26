package com.academic.academeet.controller;

import com.academic.academeet.domain.model.Course;
import com.academic.academeet.domain.model.User;
import com.academic.academeet.domain.service.ICourseService;
import com.academic.academeet.domain.service.StudentService;
import com.academic.academeet.resource.CourseResource;
import com.academic.academeet.resource.SaveCourseResource;
import com.academic.academeet.resource.SaveUserResource;
import com.academic.academeet.resource.UserResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Get Courses", description = "Get All Courses",
            tags = "Courses")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Should retrieve all Courses",
            content = @Content(mediaType = "application/json"))})
    @GetMapping("/courses")
    public List<CourseResource> getAllCourses() {
        return courseService.getAllCourses()
                .stream()
                .map(
                        course -> mapper.map(course, CourseResource.class)
                ).collect(Collectors.toList());
    }

    @Operation(summary = "Create a Course", description = "Create a Course",
            tags = "Courses")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Should retrieve all Courses",
            content = @Content(mediaType = "application/json"))})
    @PostMapping("/courses")
    public CourseResource saveCourse(@RequestBody SaveCourseResource resource) {
        Course course = convertToEntity(resource);
        return convertToResource(courseService.createCourse(course));
    }

    @Operation(summary = "Update Course", description = "Update Course",
            tags = "Courses")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Should update a Course",
            content = @Content(mediaType = "application/json"))})
    @PutMapping("/courses/{id}")
    public CourseResource updateCourse(@PathVariable Long id, @RequestBody SaveCourseResource resource) {
        Course course = convertToEntity(resource);
        return convertToResource(courseService.updateCourse(id,course));
    }

    @Operation(summary = "Delete Course", description = "Delete Course",
            tags = "Courses")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Should delete a Course",
            content = @Content(mediaType = "application/json"))})
    @DeleteMapping("/courses/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) { return courseService.deleteCourse(id); }

    @Operation(summary = "Assign Course", description = "Assign a Course",
            tags = "Student-Course")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Should assign a Course to a Student",
            content = @Content(mediaType = "application/json"))})
    @PostMapping("courses/{courseid}/students/{studentid}")
    public CourseResource assignStudentCourse(@PathVariable Long courseid, @PathVariable Long studentid){
        return convertToResource(courseService.assignStudentCourse(courseid,studentid));
    }

    @Operation(summary = "Unassign Course", description = "Unassign a Course",
            tags = "Student-Course")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Should unassign a Course to a Student",
            content = @Content(mediaType = "application/json"))})
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
