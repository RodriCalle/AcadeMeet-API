package com.academic.academeet.controller;

import com.academic.academeet.domain.model.University;
import com.academic.academeet.domain.service.IUniversityService;
import com.academic.academeet.resource.SaveUniversityResource;
import com.academic.academeet.resource.UniversityResource;
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
public class UniversityController {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private IUniversityService universityService;

    public University convertToEntity(SaveUniversityResource resource){
        return modelMapper.map(resource,University.class);
    }
    public UniversityResource convertToResource(University entity){
        return modelMapper.map(entity,UniversityResource.class);
    }

    @Operation(summary = "Add University"
            , description = "Add University By Id"
            , tags = {"universities"})
    @PostMapping("/universities")
    public UniversityResource createUniversity(@Valid @RequestBody SaveUniversityResource resource){
        University uni = convertToEntity(resource);
        return convertToResource(universityService.createUniversity(uni));
    }

    @Operation(summary = "Get University"
            , description = "Get University By Id"
            , tags = {"universities"})
    @GetMapping("/universities/{universityId}")
    public UniversityResource getUniversityById(@PathVariable Long universityId){
        University uni = universityService.getUniversityById(universityId);
        return convertToResource(uni);
    }

    @Operation(summary = "Update University"
            , description = "Update University By Id"
            , tags = {"universities"})
    @PutMapping("/universities/{universityId}")
    public UniversityResource updateUniversity(@PathVariable Long universityId, @Valid @RequestBody SaveUniversityResource resource){
        University uni = convertToEntity(resource);
        return convertToResource(universityService.updateUniversity(universityId,uni));
    }

    @Operation(summary = "Delete University"
            , description = "Delete University By Id"
            , tags = {"universities"})
    @DeleteMapping("/universities/{universityId}")
    public ResponseEntity<?> deleteUniversity(@PathVariable Long universityId){
        universityService.deleteUniversity(universityId);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Get All Universities"
            , description = "Get All Universities"
            , tags = {"universities"})
    @GetMapping("/universities")
    public Page<UniversityResource> getAllUniversities(Pageable pageable){
        Page<University> universityPage = universityService.getAll(pageable);
        List<UniversityResource> resources = universityPage.getContent()
                .stream().map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources,pageable,resources.size());
    }
}
