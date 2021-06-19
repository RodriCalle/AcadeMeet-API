package com.academic.academeet.service;

import com.academic.academeet.domain.model.University;
import com.academic.academeet.domain.repository.IUniversityRepository;
import com.academic.academeet.domain.service.IUniversityService;

import com.academic.academeet.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UniversityServiceImpl implements IUniversityService {
    @Autowired
    private IUniversityRepository universityRepository;

    @Override
    public Page<University> getAll(Pageable pageable) {
        return universityRepository.findAll(pageable);
    }

    @Override
    public University createUniversity(University university) {
        return universityRepository.save(university);
    }

    @Override
    public University updateUniversity(Long universityId, University universityDetails) {
        //1Forma si funciona no retorna excepcion
        University uni =universityRepository.findById(universityId)
                .orElseThrow(()->new ResourceNotFoundException("University", "Id", universityId));
        uni.setName(universityDetails.getName());
        return universityRepository.save(uni);
        //2Forma
       /* return  universityRepository.findById(universityId)
                .map(university -> {
                    university.setName(universityDetails.getName());
                    return universityRepository.save(university);})
                .orElseThrow(()->new ResourceNotFoundException("No"));*/
    }

    @Override
    public University getUniversityById(Long universityId) {
        return universityRepository.findById(universityId)
                .orElseThrow(()->new ResourceNotFoundException("University", "Id", universityId));
    }

    @Override
    public ResponseEntity<?> deleteUniversity(Long universityId) {
        University uni = universityRepository.findById(universityId)
                .orElseThrow(()-> new ResourceNotFoundException("University", "Id", universityId));
        universityRepository.delete(uni);
        return  ResponseEntity.ok().build();
    }
}
