package com.academic.academeet.controller;

import com.academic.academeet.domain.model.Level;
import com.academic.academeet.domain.service.LevelService;
import com.academic.academeet.resource.LevelResource;
import com.academic.academeet.resource.SaveLevelResource;
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
public class LevelsController {

    @Autowired
    private LevelService levelService;

    @Autowired
    private ModelMapper mapper;


    @GetMapping("/levels")
    public Page<LevelResource> getAllLevels(Pageable pageable) {
        List<LevelResource> levels = levelService.getAllLevels(pageable)
                .getContent().stream().map(this::convertToResource)
                .collect(Collectors.toList());
        int levelCount = levels.size();
        return new PageImpl<>(levels, pageable, levelCount);
    }

    @GetMapping("/levels/{id}")
    public LevelResource getLevelById(@PathVariable Long id) {
        return convertToResource(levelService.getLevelById(id));
    }

    @PostMapping("/levels")
    public LevelResource createLevel(@Valid @RequestBody SaveLevelResource resource) {
        return convertToResource(levelService.createLevel(convertToEntity(resource)));
    }

    @PutMapping("/levels/{id}/students/{studentId}")
    public LevelResource assignLevelToStudent(@PathVariable Long id, @PathVariable Long studentId) {
        return convertToResource(levelService.assignLevelToStudent(id, studentId));
    }

    @DeleteMapping("/levels/{id}")
    public ResponseEntity<?> deleteLevel(@PathVariable Long id) {
        return levelService.deleteLevel(id);
    }

    private Level convertToEntity(SaveLevelResource resource) {
        return mapper.map(resource, Level.class);
    }
    private LevelResource convertToResource(Level entity) {
        return mapper.map(entity, LevelResource.class);
    }
}
