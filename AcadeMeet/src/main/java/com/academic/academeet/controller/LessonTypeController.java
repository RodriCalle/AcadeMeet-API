package com.academic.academeet.controller;

import com.academic.academeet.domain.model.LessonType;
import com.academic.academeet.domain.service.ILessonTypeService;
import com.academic.academeet.resource.LessonTypeResource;
import com.academic.academeet.resource.SaveLessonTypeResource;
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

    @PostMapping("/lesson_types")
    public LessonTypeResource createLessonType(@RequestBody SaveLessonTypeResource resource) {
        return convertToResource(lessonTypeService.createLessonType(convertToEntity(resource)));
    }

    @PutMapping("/lesson_types/{lessonTypeId}")
    public LessonTypeResource updateLessonType(@PathVariable Long lessonTypeId, @Valid @RequestBody SaveLessonTypeResource resource) {
        return convertToResource(lessonTypeService.updateLessonType(lessonTypeId, convertToEntity(resource)));
    }

    @GetMapping("/lesson_types/{lessonTypeId}")
    public LessonTypeResource getLessonTypeById(@PathVariable Long lessonTypeId) {
        return convertToResource(lessonTypeService.getLessonTypeById(lessonTypeId));
    }

    @DeleteMapping("/lesson_types/{lessonTypeId}")
    public ResponseEntity<?> deleteLessonType(@PathVariable Long lessonTypeId) {
        return lessonTypeService.deleteLessonType(lessonTypeId);
    }

    @GetMapping("/lesson_types")
    public Page<LessonTypeResource> getLessonTypes(Pageable pageable) {
        List<LessonTypeResource> schools = lessonTypeService.getAll(pageable)
                .getContent().stream().map(this::convertToResource).
                        collect(Collectors.toList());
        int schoolCount = schools.size();
        return new PageImpl<>(schools, pageable, schoolCount);
    }

}
