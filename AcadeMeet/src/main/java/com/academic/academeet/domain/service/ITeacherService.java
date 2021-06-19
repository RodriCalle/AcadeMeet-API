package com.academic.academeet.domain.service;

import com.academic.academeet.domain.model.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ITeacherService {
    Teacher createTeacher(Teacher teacher);
    Teacher updateTeacher(Long teacherId, Teacher teacherDetails);
    Teacher getTeacherById(Long teacherId);
    ResponseEntity<?> deleteTeacher(Long teacherId);

    Page<Teacher> getAll(Pageable pageable);
    Page<Teacher> getAllByCarrerId(Long carrerId, Pageable pageable);

}
