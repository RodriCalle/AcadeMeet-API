package com.academic.academeet.service;


import com.academic.academeet.domain.model.Carrer;
import com.academic.academeet.domain.model.Tutor;
import com.academic.academeet.domain.model.University;
import com.academic.academeet.domain.repository.ICarrerRepository;
import com.academic.academeet.domain.repository.ITutorRepository;
import com.academic.academeet.domain.repository.IUniversityRepository;
import com.academic.academeet.domain.service.ICarrerService;
import com.academic.academeet.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CarrerServiceImpl implements ICarrerService {
    @Autowired
    private IUniversityRepository universityRepository;
    @Autowired
    private ICarrerRepository carrerRepository;
    @Autowired
    private ITutorRepository tutorRepository;

    @Override
    public Carrer createCarrer(Long universityId, Carrer carrer) {
        return universityRepository.findById(universityId).map(university->{
            carrer.setUniversity(university);
            return carrerRepository.save(carrer);
        }).orElseThrow(()->new ResourceNotFoundException("University", "Id", universityId));
    }

    @Override
    public Carrer updateCarrer(Long universityId, Long carrerId, Carrer carrerDetails) {
        University university = universityRepository.findById(universityId)
                .orElseThrow(()->new ResourceNotFoundException("University", "Id", universityId));

        return carrerRepository.findById(carrerId).map(carrer -> {
            carrer.setName(carrerDetails.getName());
            carrer.setUniversity(university);
            return carrerRepository.save(carrer);
            }).orElseThrow(() -> new ResourceNotFoundException("Carrer", "Id", carrerId));
    }

    @Override
    public Carrer getCarrerById(Long carrerId) {
        return carrerRepository.findById(carrerId)
                .orElseThrow(()-> new ResourceNotFoundException("Carrer", "Id",carrerId));
    }

    @Override
    public ResponseEntity<?> deleteCarrer(Long carrerId) {
        return carrerRepository.findById(carrerId).map(carrer -> {
            carrerRepository.delete(carrer);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Carrer", "Id", carrerId));
    }

    @Override
    public Carrer unassignCarrerTutor(Long carrerId, Long tutorId) {
        Tutor tutor = tutorRepository.findById(tutorId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Tutor", "Id", tutorId));
        return carrerRepository.findById(carrerId).map(
                carrer -> carrerRepository.save(carrer.untagWith(tutor)))
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Carrer", "Id", carrerId));
    }

    @Override
    public Carrer assignCarrerTutor(Long carrerId, Long tutorId) {
        Tutor tutor = tutorRepository.findById(tutorId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Tutor", "Id", tutorId));
        return carrerRepository.findById(carrerId).map(
                carrer -> carrerRepository.save(carrer.tagWith(tutor)))
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Carrer", "Id", carrerId));
    }

    @Override
    public Page<Carrer> getAll(Pageable pageable) {
        return carrerRepository.findAll(pageable);
    }
}