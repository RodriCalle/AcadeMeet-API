package com.academic.academeet.service;

import com.academic.academeet.domain.model.Carrer;
import com.academic.academeet.domain.model.Teacher;
import com.academic.academeet.domain.repository.ICarrerRepository;
import com.academic.academeet.domain.repository.ITeacherRepository;
import com.academic.academeet.domain.service.ITeacherService;
import com.academic.academeet.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements ITeacherService {
    @Autowired
    private ICarrerRepository carrerRepository;
    @Autowired
    private ITeacherRepository teacherRepository;

    @Override
    public Teacher createTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @Override
    public Teacher updateTeacher(Long teacherId, Teacher teacherDetails) {
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(()-> new ResourceNotFoundException("Teacher", "Id", teacherId));

        teacher.setName(teacherDetails.getName());
        return teacherRepository.save(teacher);
    }

    @Override
    public Teacher getTeacherById(Long teacherId) {
        return teacherRepository.findById(teacherId)
                .orElseThrow(()-> new ResourceNotFoundException("Teacher", "Id", teacherId));
    }

    @Override
    public ResponseEntity<?> deleteTeacher(Long teacherId) {
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(()-> new ResourceNotFoundException("Teacher", "Id", teacherId));
        teacherRepository.delete(teacher);
        return ResponseEntity.ok().build();
    }

    @Override
    public Page<Teacher> getAll(Pageable pageable) {
        return teacherRepository.findAll(pageable);
    }

    @Override
    public Page<Teacher> getAllByCarrerId(Long carrerId, Pageable pageable) {
        return carrerRepository.findById(carrerId).map(carrer-> {
            List<Teacher> teachers = carrer.getTeachers();
            int teachersCount = teachers.size();
            return new PageImpl<>(teachers, pageable, teachersCount);
        }).orElseThrow(()-> new ResourceNotFoundException("Carrer","Id", carrerId));
    }
}
