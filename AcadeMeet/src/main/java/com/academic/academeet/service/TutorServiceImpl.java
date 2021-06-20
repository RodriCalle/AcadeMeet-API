package com.academic.academeet.service;

import com.academic.academeet.domain.model.Tutor;
import com.academic.academeet.domain.repository.ICarrerRepository;
import com.academic.academeet.domain.repository.ITutorRepository;
import com.academic.academeet.domain.service.ITutorService;
import com.academic.academeet.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TutorServiceImpl implements ITutorService {
    @Autowired
    private ICarrerRepository carrerRepository;
    @Autowired
    private ITutorRepository tutorRepository;

    @Override
    public Tutor createTutor(Tutor tutor) {
        return tutorRepository.save(tutor);
    }

    @Override
    public Tutor updateTutor(Long tutorId, Tutor tutorDetails) {
        Tutor tutor = tutorRepository.findById(tutorId)
                .orElseThrow(()-> new ResourceNotFoundException("Tutor", "Id", tutorId));

        tutor.setName(tutorDetails.getName());
        return tutorRepository.save(tutor);
    }

    @Override
    public Tutor getTutorById(Long tutorId) {
        return tutorRepository.findById(tutorId)
                .orElseThrow(()-> new ResourceNotFoundException("Tutor", "Id", tutorId));
    }

    @Override
    public ResponseEntity<?> deleteTutor(Long tutorId) {
        Tutor tutor = tutorRepository.findById(tutorId)
                .orElseThrow(()-> new ResourceNotFoundException("Tutor", "Id", tutorId));
        tutorRepository.delete(tutor);
        return ResponseEntity.ok().build();
    }

    @Override
    public Page<Tutor> getAll(Pageable pageable) {
        return tutorRepository.findAll(pageable);
    }

    @Override
    public Page<Tutor> getAllByCarrerId(Long carrerId, Pageable pageable) {
        return carrerRepository.findById(carrerId).map(carrer-> {
            List<Tutor> tutors = carrer.getTutors();
            int tutorsCount = tutors.size();
            return new PageImpl<>(tutors, pageable, tutorsCount);
        }).orElseThrow(()-> new ResourceNotFoundException("Carrer","Id", carrerId));
    }
}
