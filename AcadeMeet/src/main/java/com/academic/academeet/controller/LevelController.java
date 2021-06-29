package com.academic.academeet.controller;

import com.academic.academeet.domain.model.Level;
import com.academic.academeet.domain.service.ILevelService;
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
public class LevelController {

    @Autowired
    private ILevelService levelService;

    @Autowired
    private ModelMapper mapper;

    private Level convertToEntity(SaveLevelResource resource) {
        return mapper.map(resource, Level.class);
    }
    private LevelResource convertToResource(Level entity) {
        return mapper.map(entity, LevelResource.class);
    }

    @PostMapping("/levels")
    public LevelResource createLevel(@Valid @RequestBody SaveLevelResource resource) {
        return convertToResource(levelService.createLevel(convertToEntity(resource)));
    }

    @PutMapping("/levels/{levelId}")
    public LevelResource updateLevel(@PathVariable Long levelId, @Valid @RequestBody SaveLevelResource resource) {
        return convertToResource(levelService.updateLevel(levelId, convertToEntity(resource)));
    }

    @GetMapping("/levels/{levelId}")
    public LevelResource getLevelById(@PathVariable Long levelId) {
        return convertToResource(levelService.getLevelById(levelId));
    }

    @DeleteMapping("/levels/{levelId}")
    public ResponseEntity<?> deleteLevel(@PathVariable Long levelId) {
        return levelService.deleteLevel(levelId);
    }

    @GetMapping("/levels")
    public Page<LevelResource> getAllLevels(Pageable pageable) {
        List<LevelResource> levels = levelService.getAll(pageable)
                .getContent().stream().map(this::convertToResource)
                .collect(Collectors.toList());
        int levelCount = levels.size();
        return new PageImpl<>(levels, pageable, levelCount);
    }

    /*
    @PostMapping("/levels/{levelId}/students/{studentId}")
    public LevelResource assignLevelToStudent(@PathVariable Long levelId, @PathVariable Long studentId) {
        return convertToResource(levelService.assignLevelToStudent(levelId, studentId));
    }*/
}
