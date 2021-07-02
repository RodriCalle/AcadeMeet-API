package com.academic.academeet.domain.service;

import com.academic.academeet.domain.model.University;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface IUniversityService {
    University createUniversity(University university);
    University updateUniversity(Long universityId, University universityDetails);
    University getUniversityById(Long universityId);
    ResponseEntity<?> deleteUniversity(Long universityId);
    Page<University> getAll(Pageable pageable);
}
