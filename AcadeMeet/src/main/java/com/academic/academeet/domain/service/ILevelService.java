package com.academic.academeet.domain.service;

import com.academic.academeet.domain.model.Level;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ILevelService {
    Page<Level> getAllLevels(Pageable pageable);
    Level getLevelById(Long id);
    Level createLevel(Level level);
    Level updateLevel(Long id, Level level);
    ResponseEntity<?> deleteLevel(Long id);
    Level assignLevelToStudent(Long id, Long studentId);
}
