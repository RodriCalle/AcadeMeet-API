package com.academic.academeet.service;

import com.academic.academeet.domain.model.Level;
import com.academic.academeet.domain.repository.ILevelRepository;
import com.academic.academeet.domain.repository.IStudentRepository;
import com.academic.academeet.domain.service.ILevelService;
import com.academic.academeet.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class LevelServiceImpl implements ILevelService {

    @Autowired
    private ILevelRepository levelRepository;

    @Autowired
    private IStudentRepository studentRepository;

    @Override
    public Page<Level> getAll(Pageable pageable) {
        return levelRepository.findAll(pageable);
    }

    @Override
    public Level createLevel(Level level) {
        return levelRepository.save(level);
    }

    @Override
    public Level updateLevel(Long levelId, Level levelDetails) {
        Level level = levelRepository.findById(levelId)
                .orElseThrow(()->new ResourceNotFoundException("Level", "Id", levelId));
        level.setName(levelDetails.getName());
        return levelRepository.save(level);
    }

    @Override
    public Level getLevelById(Long levelId) {
        return  levelRepository.findById(levelId)
                .orElseThrow(() -> new ResourceNotFoundException("Level", "Id", levelId));
    }


    @Override
    public ResponseEntity<?> deleteLevel(Long levelId) {
        return levelRepository.findById(levelId)
                .map(Level -> {
                    levelRepository.delete(Level);
                    return ResponseEntity.ok().build();
                })
                .orElseThrow(() -> new ResourceNotFoundException("Level", "Id", levelId));
    }


    @Override
    public Level assignLevelToStudent(Long levelId, Long studentId) {
        return studentRepository.findById(studentId)
                .map(student -> {
                    Level level = levelRepository.findById(levelId)
                        .orElseThrow(() -> new ResourceNotFoundException("Level", "Id", levelId));
                    studentRepository.save(student);
                    return level;
                })
                .orElseThrow(() -> new ResourceNotFoundException("Student", "Id", studentId));
    }
}
