package com.academic.academeet.domain.service;


import com.academic.academeet.domain.model.Carrer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ICarrerService {
    Page<Carrer> getAll(Pageable pageable);
    Page<Carrer> getAllByUniversityId(Long universityId, Pageable pageable);

    Carrer createCarrer(Long universityId, Carrer carrer);
    Carrer updateCarrer(Long universityId, Long carrerId, Carrer carrer);
    Carrer getCarrerById(Long carrerId);
    ResponseEntity<?> deleteCarrer(Long universityId, Long carrerId);

    Carrer assignCarrerTutor(Long carrerId, Long teacherId);
    Carrer unassignCarrerTutor(Long carrerId, Long teacherId);
}
