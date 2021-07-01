package com.academic.academeet.controller;

import com.academic.academeet.domain.model.LessonType;
import com.academic.academeet.domain.service.ILessonTypeService;
import com.academic.academeet.resource.LessonTypeResource;
import com.academic.academeet.resource.SaveLessonTypeResource;
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
public class LessonTypeController {
    @Autowired
    private ILessonTypeService lessonTypeService;

    @Autowired
    private ModelMapper mapper;

    private LessonType convertToEntity(SaveLessonTypeResource resource) {
        return mapper.map(resource, LessonType.class);
    }
    private LessonTypeResource convertToResource(LessonType entity) {
        return mapper.map(entity, LessonTypeResource.class);
    }
    @Operation(summary = "Save a Lesson Type", description = "Save a Lesson Type", tags = {"lesson-types"})
    @ApiResponse(
            responseCode = "200",
            description = "Return the Lesson Type saved",
            content = @Content(mediaType = "application/json")
    )
    @PostMapping("/lesson_types")
    public LessonTypeResource createLessonType(@RequestBody SaveLessonTypeResource resource) {
        return convertToResource(lessonTypeService.createLessonType(convertToEntity(resource)));
    }
    @Operation(summary = "Update a Lesson Type", description = "Update a Lesson Type given an id", tags = {"lesson-types"})
    @ApiResponse(
            responseCode = "200",
            description = "Return a Lesson Type updated",
            content = @Content(mediaType = "application/json")
    )
    @PutMapping("/lesson_types/{lessonTypeId}")
    public LessonTypeResource updateLessonType(@PathVariable Long lessonTypeId, @Valid @RequestBody SaveLessonTypeResource resource) {
        return convertToResource(lessonTypeService.updateLessonType(lessonTypeId, convertToEntity(resource)));
    }
    @Operation(summary = "Get Lesson Type by id", description = "Get a Lesson Type given an id", tags = {"lesson-types"})
    @ApiResponse(
            responseCode = "200",
            description = "Return a Lesson Type",
            content = @Content(mediaType = "application/json")
    )
    @GetMapping("/lesson_types/{lessonTypeId}")
    public LessonTypeResource getLessonTypeById(@PathVariable Long lessonTypeId) {
        return convertToResource(lessonTypeService.getLessonTypeById(lessonTypeId));
    }
    @Operation(summary = "Delete a Lesson Type", description = "remove a Lesson Type given an id", tags = {"lesson-types"})
    @ApiResponse(
            responseCode = "200",
            description = "Return ResponseEntity",
            content = @Content(mediaType = "application/json")
    )
    @DeleteMapping("/lesson_types/{lessonTypeId}")
    public ResponseEntity<?> deleteLessonType(@PathVariable Long lessonTypeId) {
        return lessonTypeService.deleteLessonType(lessonTypeId);
    }
    @Operation(summary = "Get Lesson Types", description = "Get All Lesson Types by Pages", tags = {"lesson-types"})
    @ApiResponse(
            responseCode = "200",
            description = "All Lesson Types returned",
            content = @Content(mediaType = "application/json")
    )
    @GetMapping("/lesson_types")
    public Page<LessonTypeResource> getLessonTypes(Pageable pageable) {
        List<LessonTypeResource> schools = lessonTypeService.getAll(pageable)
                .getContent().stream().map(this::convertToResource).
                        collect(Collectors.toList());
        int schoolCount = schools.size();
        return new PageImpl<>(schools, pageable, schoolCount);
    }

}
