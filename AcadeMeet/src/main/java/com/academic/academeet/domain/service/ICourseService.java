package com.academic.academeet.domain.service;

import com.academic.academeet.domain.model.Course;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ICourseService {
    List<Course> getAllCourses();
    Course getCourseById(Long courseid);
    Course createCourse(Course course);
    Course updateCourse(Long courseid,Course course);
    ResponseEntity<?> deleteCourse(Long courseid);

    Course assignStudentCourse(Long courseid, Long studentid);
    Course unassignStudentCourse(Long courseid, Long studentid);

}
