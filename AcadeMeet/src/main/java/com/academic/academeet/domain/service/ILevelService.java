package com.academic.academeet.domain.service;

import com.academic.academeet.domain.model.Level;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ILevelService {
    Level createLevel(Level level);
    Level updateLevel(Long levelId, Level level);
    Level getLevelById(Long levelId);
    ResponseEntity<?> deleteLevel(Long levelId);
    Page<Level> getAll(Pageable pageable);

    Level assignLevelToStudent(Long levelId, Long studentId);
}
