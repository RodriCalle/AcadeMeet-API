package com.academic.academeet.service;


import com.academic.academeet.domain.model.Carrer;
import com.academic.academeet.domain.model.Teacher;
import com.academic.academeet.domain.repository.ICarrerRepository;
import com.academic.academeet.domain.repository.ITeacherRepository;
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
    private ITeacherRepository teacherRepository;

    @Override
    public Carrer createCarrer(Long universityId, Carrer carrer) {
        //Bien asi se debe hacer
        return universityRepository.findById(universityId).map(university->{
            carrer.setUniversity(university);
            return carrerRepository.save(carrer);
        }).orElseThrow(()->new ResourceNotFoundException("University", "Id", universityId));
    }

    @Override
    public Carrer updateCarrer(Long universityId, Long carrerId, Carrer carrer) {
        if(!universityRepository.existsById(universityId))
            throw new ResourceNotFoundException("University", "Id", universityId);

        return carrerRepository.findById(carrerId).map(carrer1 -> {
            carrer1.setName(carrer.getName());
            return carrerRepository.save(carrer1);
            }).orElseThrow(() -> new ResourceNotFoundException("Carrer", "Id", carrerId));
    }

    @Override
    public Carrer getCarrerById(Long carrerId) {
        return carrerRepository.findById(carrerId)
                .orElseThrow(()-> new ResourceNotFoundException("Carrer", "Id",carrerId));
    }

    @Override
    public ResponseEntity<?> deleteCarrer(Long universityId, Long carrerId) {
        if(!universityRepository.existsById(universityId))
            throw new ResourceNotFoundException("University", "Id", universityId);

        return carrerRepository.findById(carrerId).map(carrer1 -> {
            carrerRepository.delete(carrer1);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Carrer", "Id", carrerId));
    }

    @Override
    public Carrer unassignCarrerTeacher(Long carrerId, Long teacherId) {
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Teacher", "Id", teacherId));
        return carrerRepository.findById(carrerId).map(
                carrer -> carrerRepository.save(carrer.untagWith(teacher)))
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Carrer", "Id", carrerId));
    }

    @Override
    public Carrer assignCarrerTeacher(Long carrerId, Long teacherId) {
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Teacher", "Id", teacherId));
        return carrerRepository.findById(carrerId).map(
                carrer -> carrerRepository.save(carrer.tagWith(teacher)))
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Carrer", "Id", carrerId));
    }

    @Override
    public Page<Carrer> getAll(Pageable pageable) {
        return carrerRepository.findAll(pageable);
    }

    @Override
    public Page<Carrer> getAllByUniversityId(Long universityId, Pageable pageable) {
        return carrerRepository.findAllByUniversityId(universityId,pageable);
    }
}