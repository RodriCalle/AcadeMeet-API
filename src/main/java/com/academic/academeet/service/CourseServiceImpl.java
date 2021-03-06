package com.academic.academeet.service;

import com.academic.academeet.domain.model.Course;
import com.academic.academeet.domain.repository.ICourseRepository;
import com.academic.academeet.domain.service.ICourseService;
import com.academic.academeet.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements ICourseService {

    @Autowired
    private ICourseRepository courseRepository;

    @Override
    public List<Course> getAll() {
        return courseRepository.findAll();
    }

    @Override
    public Optional<Course> getCourseById(Long courseid) {
        return courseRepository.findById(courseid);
    }

    @Override
    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Course updateCourse(Long courseid, Course course) {
        return courseRepository.findById(courseid).map(
                Course -> {
                    Course.setName(course.getName());
                    return courseRepository.save(Course);
                }
        ).orElseThrow(() -> new ResourceNotFoundException("Course", "Id", course));
    }

    @Override
    public ResponseEntity<?> deleteCourse(Long courseid) {
        return courseRepository.findById(courseid)
                .map(course -> {
                    courseRepository.delete(course);
                    return ResponseEntity.ok().build();
                })
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Course", "Id", courseid
                ));
    }

    /*@Override
    public Course assignStudentCourse(Long courseid, Long studentid) {
        Student student = IStudentRepository.findById(studentid)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Student", "Id", studentid));
        return courseRepository.findById(courseid).map(
                course -> courseRepository.save(course.tagWith(student)))
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Course", "Id", courseid));
    }

    @Override
    public Course unassignStudentCourse(Long courseid, Long studentid) {
        Student student = IStudentRepository.findById(studentid)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Student", "Id", studentid));
        return courseRepository.findById(courseid).map(
                course -> courseRepository.save(course.untagWith(student)))
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Course", "Id", courseid));
    }*/
}
