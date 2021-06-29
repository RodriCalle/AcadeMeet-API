package com.academic.academeet.domain.service;

import com.academic.academeet.domain.model.Course;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ICourseService {
    Course createCourse(Course course);
    Course updateCourse(Long courseid,Course course);
    Course getCourseById(Long courseid);
    ResponseEntity<?> deleteCourse(Long courseid);
    List<Course> getAll();
}
