package com.academic.academeet.controller;

import com.academic.academeet.domain.model.LessonType;
import com.academic.academeet.domain.service.LessonTypeService;
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
    private LessonTypeService lessonTypeService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping("/Lesson_types")
    public Page<LessonTypeResource> getLessonTypes(Pageable pageable) {
        List<LessonTypeResource> schools = lessonTypeService.getAllLessonTypes(pageable)
                .getContent().stream().map(this::convertToResource).
                        collect(Collectors.toList());
        int schoolCount = schools.size();
        return new PageImpl<>(schools, pageable, schoolCount);
    }

    @GetMapping("/Lesson_types/{id}")
    public LessonTypeResource getLessonTypeById(@PathVariable Long id) {
        return convertToResource(lessonTypeService.getLessonTypeById(id));
    }

    @PostMapping("/Lesson_types")
    public LessonTypeResource createLessonType(@RequestBody SaveLessonTypeResource resource) {
        return convertToResource(lessonTypeService.saveLessonType(convertToEntity(resource)));
    }


    @PutMapping("/Lesson_type/{id}")
    public LessonTypeResource updateLessonType(@PathVariable Long id, @Valid @RequestBody SaveLessonTypeResource resource) {
        return convertToResource(lessonTypeService.updateLessonType(id, convertToEntity(resource)));
    }

    @DeleteMapping("/lesson_type/{id}")
    public ResponseEntity<?> deleteLessonType(@PathVariable Long id) {
        return lessonTypeService.deleteLessonType(id);
    }

    private LessonType convertToEntity(SaveLessonTypeResource resource) {
        return mapper.map(resource, LessonType.class);
    }
    private LessonTypeResource convertToResource(LessonType entity) {
        return mapper.map(entity, LessonTypeResource.class);
    }
}
