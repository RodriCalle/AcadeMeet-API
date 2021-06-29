package com.academic.academeet.domain.service;

import com.academic.academeet.domain.model.School;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ISchoolService {
    School createSchool(School school);
    School updateSchool(Long schoolId, School school);
    School getSchoolById(Long schoolId);
    ResponseEntity<?> deleteSchool(Long schoolId);
    Page<School> getAll(Pageable pageable);
}
