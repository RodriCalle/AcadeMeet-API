package com.academic.academeet.domain.repository;

import com.academic.academeet.domain.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICourseRepository extends JpaRepository<Course,Long> {
    List<Course> findAllByStudentsId(Long studentid);
}
