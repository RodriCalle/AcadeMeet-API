package com.academic.academeet.service;

import com.academic.academeet.domain.model.Level;
import com.academic.academeet.domain.repository.LevelRepository;
import com.academic.academeet.domain.repository.StudentRepository;
import com.academic.academeet.domain.service.LevelService;
import com.academic.academeet.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class LevelServiceImpl implements LevelService {

    @Autowired
    private LevelRepository levelRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Page<Level> getAllLevels(Pageable pageable) {
        return levelRepository.findAll(pageable);
    }

    @Override
    public Level getLevelById(Long id) {
        return  levelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Level", "Id", id));
    }

    @Override
    public Level createLevel(Level level) {
        return levelRepository.save(level);
    }

    @Override
    public Level updateLevel(Long id, Level level) {
        return levelRepository.findById(id)
                .map(Level -> {
                    Level.setName(level.getName());
                    return levelRepository.save(level);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Level", "Id", id));
    }

    @Override
    public ResponseEntity<?> deleteLevel(Long id) {
        return levelRepository.findById(id)
                .map(Level -> {
                    levelRepository.delete(Level);
                    return ResponseEntity.ok().build();
                })
                .orElseThrow(() -> new ResourceNotFoundException("Level", "Id", id));
    }

    @Override
    public Level assignLevelToStudent(Long id, Long studentId) {
        return studentRepository.findById(studentId)
                .map(student -> {
                    Level level = levelRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Level", "Id", id));
                    student.setLevel(level);
                    studentRepository.save(student);
                    return level;
                })
                .orElseThrow(() -> new ResourceNotFoundException("Student", "Id", studentId));
    }
}
