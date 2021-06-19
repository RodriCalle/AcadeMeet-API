package com.academic.academeet.controller;


import com.academic.academeet.domain.model.Carrer;
import com.academic.academeet.domain.service.ICarrerService;
import com.academic.academeet.domain.service.ITeacherService;
import com.academic.academeet.domain.service.IUniversityService;
import com.academic.academeet.resource.CarrerResource;
import com.academic.academeet.resource.SaveCarrerResource;
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
    private ITeacherService teacherService;

    public Carrer convertToEntity(SaveCarrerResource resource){
        return modelMapper.map(resource,Carrer.class);
    }
    public CarrerResource convertToResource(Carrer entity){
        return modelMapper.map(entity,CarrerResource.class);
    }

    @PostMapping("/universities/{universityId}/carrers")
    public CarrerResource createCarrer(@PathVariable Long universityId, @Valid @RequestBody SaveCarrerResource resource){
        Carrer carrer = convertToEntity(resource);
        return convertToResource(carrerService.createCarrer(universityId,carrer));
    }

    @GetMapping("/carrers/{carrerId}")
    public CarrerResource getCarrerById(@PathVariable Long carrerId){
        Carrer carrer = carrerService.getCarrerById(carrerId);
        return convertToResource(carrer);
    }

    @PutMapping("/universities/{universityId}/carrers/{carrerId}")
    public CarrerResource updateCarrer(@PathVariable Long universityId,@PathVariable Long carrerId,@Valid @RequestBody SaveCarrerResource resource){
        Carrer carrer = convertToEntity(resource);
        return convertToResource(carrerService.updateCarrer(universityId,carrerId,carrer));
    }

    @DeleteMapping("/universities/{universityId}/carrers/{carrerId}")
    public ResponseEntity<?> deleteCarrer(@PathVariable Long universityId,@PathVariable Long carrerId){
        carrerService.deleteCarrer(universityId,carrerId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/carrers")
    public Page<CarrerResource> getAllCarrers(Pageable pageable){
        Page<Carrer> carrerPage = carrerService.getAll(pageable);
        List<CarrerResource> resources = carrerPage.getContent()
                .stream().map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources,pageable,resources.size());
    }

    @PostMapping("/carrers/{carrerId}/teachers/{teacherId}")
    public CarrerResource assignCarrerTeacher(@PathVariable Long carrerId, @PathVariable Long teacherId) {
        return convertToResource(carrerService.assignCarrerTeacher(carrerId, teacherId));
    }

    @DeleteMapping("/posts/{postId}/tags/{tagId}")
    public CarrerResource unassignCarrerTeacher(@PathVariable Long carrerId, @PathVariable Long teacherId) {
        return convertToResource(carrerService.unassignCarrerTeacher(carrerId, teacherId));
    }

}
