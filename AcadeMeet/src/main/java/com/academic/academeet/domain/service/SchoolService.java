package com.academic.academeet.domain.service;

import com.academic.academeet.domain.model.School;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface SchoolService {
    Page<School> getAllSchools(Pageable pageable);
    School createSchool(School school);
    School updateSchool(Long id, School school);
    School getSchoolById(Long id);
    ResponseEntity<?> deleteSchool(Long id);
}
