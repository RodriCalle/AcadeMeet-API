package com.academic.academeet.controller;

import com.academic.academeet.domain.model.Carrer;
import com.academic.academeet.domain.service.ICarrerService;
import com.academic.academeet.domain.service.ITutorService;
import com.academic.academeet.domain.service.IUniversityService;
import com.academic.academeet.resource.CarrerResource;
import com.academic.academeet.resource.SaveCarrerResource;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TutorCarrerController {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private IUniversityService universityService;
    @Autowired
    private ICarrerService carrerService;
    @Autowired
    private ITutorService tutorService;

    public Carrer convertToEntity(SaveCarrerResource resource){
        return modelMapper.map(resource,Carrer.class);
    }
    public CarrerResource convertToResource(Carrer entity){
        return modelMapper.map(entity,CarrerResource.class);
    }


    @Operation(summary = "Assign Carrer To Tutor"
            , description = "Assign Carrer To Tutor with Id"
            , tags = {"tutor_carrers"})
    @PostMapping("/carrers/{carrerId}/tutors/{tutorId}")
    public CarrerResource assignCarrerTutor(@PathVariable Long carrerId, @PathVariable Long tutorId) {
        return convertToResource(carrerService.assignCarrerTutor(carrerId, tutorId));
    }

    @Operation(summary = "Unassign Carrer To Tutor"
            , description = "Unassign Carrer To Tutor with Id"
            , tags = {"tutor_carrers"})
    @DeleteMapping("/carrers/{carrerId}/tutors/{tutorId}")
    public CarrerResource unassignCarrerTutor(@PathVariable Long carrerId, @PathVariable Long tutorId) {
        return convertToResource(carrerService.unassignCarrerTutor(carrerId, tutorId));
    }
}
