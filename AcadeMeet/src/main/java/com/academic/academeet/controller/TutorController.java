package com.academic.academeet.controller;

import com.academic.academeet.domain.model.Tutor;
import com.academic.academeet.domain.service.ICarrerService;
import com.academic.academeet.domain.service.ITutorService;
import com.academic.academeet.resource.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class TutorController {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ICarrerService carrerService;
    @Autowired
    private ITutorService tutorService;

    public Tutor convertToEntity(SaveTutorResource resource){
        return modelMapper.map(resource, Tutor.class);
    }
    public TutorResource convertToResource(Tutor entity){
        return modelMapper.map(entity, TutorResource.class);
    }

    @PostMapping("/tutors")
    public TutorResource createTutor(@Valid @RequestBody SaveTutorResource resource){
        Tutor tutor = convertToEntity(resource);
        return convertToResource(tutorService.createTutor(tutor));
    }

    @GetMapping("/tutors/{tutorId}")
    public TutorResource getTutorById(@PathVariable Long tutorId){
        Tutor tutor = tutorService.getTutorById(tutorId);
        return convertToResource(tutor);
    }

    @PutMapping("/tutors/{tutorId}")
    public TutorResource updateTutor(@PathVariable Long tutorId, @Valid @RequestBody SaveTutorResource resource){
        Tutor tutor = convertToEntity(resource);
        return convertToResource(tutorService.updateTutor(tutorId, tutor));
    }

    @DeleteMapping("/tutors/{tutorId}")
    public ResponseEntity<?> deleteTutor(@PathVariable Long tutorId){
        tutorService.deleteTutor(tutorId);
        return ResponseEntity.ok().build();
    }


}
