package com.academic.academeet.service;

import com.academic.academeet.domain.model.School;
import com.academic.academeet.domain.repository.SchoolRepository;
import com.academic.academeet.domain.service.ISchoolService;
import com.academic.academeet.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SchoolServiceImpl implements ISchoolService {

    @Autowired
    private SchoolRepository schoolRepository;

    @Override
    public Page<School> getAllSchools(Pageable pageable) {
        return schoolRepository.findAll(pageable);
    }

    @Override
    public School createSchool(School school) {
        return schoolRepository.save(school);
    }

    @Override
    public School updateSchool(Long id, School school) {
        return schoolRepository.findById(id)
                .map(varSchool -> {
                    varSchool.setLocation(school.getLocation());
                    varSchool.setName(school.getName());
                    return schoolRepository.save(varSchool);
                })
                .orElseThrow(() -> new ResourceNotFoundException("School", "Id", id));
    }

    @Override
    public School getSchoolById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<?> deleteSchool(Long id) {
        return schoolRepository.findById(id)
                .map(varSchool -> {
                    schoolRepository.delete(varSchool);
                    return ResponseEntity.ok().build();
                })
                .orElseThrow(() -> new ResourceNotFoundException("School", "Id", id));
    }
}
