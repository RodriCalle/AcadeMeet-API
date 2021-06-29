package com.academic.academeet.domain.service;

import com.academic.academeet.domain.model.Tutor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ITutorService {
    Tutor createTutor(Tutor tutor);
    Tutor updateTutor(Long tutorId, Tutor tutorDetails);
    Tutor getTutorById(Long tutorId);
    ResponseEntity<?> deleteTutor(Long tutorId);
    Page<Tutor> getAll(Pageable pageable);

    Page<Tutor> getAllByCarrerId(Long carrerId, Pageable pageable);
}
