package com.academic.academeet.domain.service;

import com.academic.academeet.domain.model.Course;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface ICourseService {
    Course createCourse(Course course);
    Course updateCourse(Long courseid,Course course);
    Optional<Course> getCourseById(Long courseid);
    ResponseEntity<?> deleteCourse(Long courseid);
    List<Course> getAll();
}
