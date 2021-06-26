package com.academic.academeet.controller;


import com.academic.academeet.domain.model.Carrer;
import com.academic.academeet.domain.service.ICarrerService;
import com.academic.academeet.domain.service.ITutorService;
import com.academic.academeet.domain.service.IUniversityService;
import com.academic.academeet.resource.CarrerResource;
import com.academic.academeet.resource.SaveCarrerResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CarrerController {
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

    @Operation(summary = "Add Carrer"
            , description = "Add Carrer By Id"
            , tags = {"carrers"})
    @PostMapping("/universities/{universityId}/carrers")
    public CarrerResource createCarrer(@PathVariable Long universityId, @Valid @RequestBody SaveCarrerResource resource){
        Carrer carrer = convertToEntity(resource);
        return convertToResource(carrerService.createCarrer(universityId,carrer));
    }

    @Operation(summary = "Get Carrer"
            , description = "Get Carrer By Id"
            , tags = {"carrers"})
    @GetMapping("/carrers/{carrerId}")
    public CarrerResource getCarrerById(@PathVariable Long carrerId){
        Carrer carrer = carrerService.getCarrerById(carrerId);
        return convertToResource(carrer);
    }

    @Operation(summary = "Update Carrer"
            , description = "Update Carrer By Id"
            , tags = {"carrers"})
    @PutMapping("/universities/{universityId}/carrers/{carrerId}")
    public CarrerResource updateCarrer(@PathVariable Long universityId,@PathVariable Long carrerId,@Valid @RequestBody SaveCarrerResource resource){
        Carrer carrer = convertToEntity(resource);
        return convertToResource(carrerService.updateCarrer(universityId,carrerId,carrer));
    }

    @Operation(summary = "Delete Carrer"
            , description = "Delete Carrer By Id"
            , tags = {"carrers"})
    @DeleteMapping("/universities/{universityId}/carrers/{carrerId}")
    public ResponseEntity<?> deleteCarrer(@PathVariable Long universityId,@PathVariable Long carrerId){
        carrerService.deleteCarrer(universityId,carrerId);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Get All Carrers"
            , description = "Get All Carrers"
            , tags = {"carrers"})
    @GetMapping("/carrers")
    public Page<CarrerResource> getAllCarrers(Pageable pageable){
        Page<Carrer> carrerPage = carrerService.getAll(pageable);
        List<CarrerResource> resources = carrerPage.getContent()
                .stream().map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources,pageable,resources.size());
    }

    @Operation(summary = "Assign Carrer To Tutor"
            , description = "Assign Carrer To Tutor with Id"
            , tags = {"carrers"})
    @PostMapping("/carrers/{carrerId}/tutors/{tutorId}")
    public CarrerResource assignCarrerTutor(@PathVariable Long carrerId, @PathVariable Long tutorId) {
        return convertToResource(carrerService.assignCarrerTutor(carrerId, tutorId));
    }

    @Operation(summary = "Unassign Carrer To Tutor"
            , description = "Unassign Carrer To Tutor with Id"
            , tags = {"carrers"})
    @DeleteMapping("/carrers/{carrerId}/tutors/{tutorId}")
    public CarrerResource unassignCarrerTutor(@PathVariable Long carrerId, @PathVariable Long tutorId) {
        return convertToResource(carrerService.unassignCarrerTutor(carrerId, tutorId));
    }
}
