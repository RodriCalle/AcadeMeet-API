package com.academic.academeet.controller;

import com.academic.academeet.domain.model.TypeOfGrade;
import com.academic.academeet.domain.service.ITypeOfGradeService;
import com.academic.academeet.resource.SaveTypeOfGradeResource;
import com.academic.academeet.resource.TypeOfGradeResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
public class TypeOfGradeController {
    @Autowired
    private ITypeOfGradeService typeOfGradeService;

    @Autowired
    private ModelMapper mapper;

    private TypeOfGrade convertToEntity(SaveTypeOfGradeResource resource) {
        return mapper.map(resource, TypeOfGrade.class);
    }
    private TypeOfGradeResource convertToResource(TypeOfGrade entity) {
        return mapper.map(entity, TypeOfGradeResource.class);
    }
    @Operation(summary = "Get Types Of Grade", description = "Get All Types Of Grade by Pages", tags = {"types-of-grade"})
    @ApiResponse(
            responseCode = "200",
            description = "All Types Of Grade returned",
            content = @Content(mediaType = "application/json")
    )
    @GetMapping("/types_of_grade")
    public Page<TypeOfGradeResource> getTypesOfGrade(Pageable pageable) {
        List<TypeOfGradeResource> schools = typeOfGradeService.getAll(pageable)
                .getContent().stream().map(this::convertToResource).
                        collect(Collectors.toList());
        int schoolCount = schools.size();
        return new PageImpl<>(schools, pageable, schoolCount);
    }
    @Operation(summary = "Get Type Of Grade by id", description = "Get a Type Of Grade given an id", tags = {"types-of-grade"})
    @ApiResponse(
            responseCode = "200",
            description = "Return a Type Of Grade",
            content = @Content(mediaType = "application/json")
    )
    @GetMapping("/types_of_grade/{typeOfGradeId}")
    public TypeOfGradeResource getTypeOfGradeById(@PathVariable Long typeOfGradeId) {
        return convertToResource(typeOfGradeService.getTypeOfGradeById(typeOfGradeId));
    }

    @Operation(summary = "Save a Type Of Grade", description = "Save a Type Of Grade", tags = {"types-of-grade"})
    @ApiResponse(
            responseCode = "200",
            description = "Return the Type Of Grade saved",
            content = @Content(mediaType = "application/json")
    )
    @PostMapping("/types_of_grade")
    public TypeOfGradeResource createTypeOfGrade(@RequestBody SaveTypeOfGradeResource resource) {
        return convertToResource(typeOfGradeService.createTypeOfGrade(convertToEntity(resource)));
    }
    @Operation(summary = "Update a Type Of Grade", description = "Update a Type Of Grade given an id", tags = {"types-of-grade"})
    @ApiResponse(
            responseCode = "200",
            description = "Return a Type Of Grade updated",
            content = @Content(mediaType = "application/json")
    )
    @PutMapping("/types_of_grade/{typeOfGradeId}")
    public TypeOfGradeResource updateTypeOfGrade(@PathVariable Long typeOfGradeId, @Valid @RequestBody SaveTypeOfGradeResource resource) {
        return convertToResource(typeOfGradeService.updateTypeOfGrade(typeOfGradeId, convertToEntity(resource)));
    }
    @Operation(summary = "Delete a Type Of Grade", description = "remove a Type Of Grade given an id", tags = {"types-of-grade"})
    @ApiResponse(
            responseCode = "200",
            description = "Return ResponseEntity",
            content = @Content(mediaType = "application/json")
    )
    @DeleteMapping("/types_of_grade/{typeOfGradeId}")
    public ResponseEntity<?> deleteTypeOfGrade(@PathVariable Long typeOfGradeId) {
        return typeOfGradeService.deleteTypeOfGrade(typeOfGradeId);
    }
}
