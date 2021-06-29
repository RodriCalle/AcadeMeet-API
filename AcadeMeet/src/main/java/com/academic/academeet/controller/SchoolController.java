package com.academic.academeet.controller;

import com.academic.academeet.domain.model.School;
import com.academic.academeet.domain.model.University;
import com.academic.academeet.domain.service.ISchoolService;
import com.academic.academeet.resource.SaveSchoolResource;
import com.academic.academeet.resource.SchoolResource;
import com.academic.academeet.resource.UniversityResource;
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
public class SchoolController {

    @Autowired
    private ISchoolService schoolService;

    @Autowired
    private ModelMapper mapper;

    private School convertToEntity(SaveSchoolResource resource) {
        return mapper.map(resource, School.class);
    }
    private SchoolResource convertToResource(School entity) {
        return mapper.map(entity, SchoolResource.class);
    }

    @PostMapping("/schools")
    public SchoolResource createSchool(@RequestBody SaveSchoolResource resource) {
            return convertToResource(schoolService.createSchool(convertToEntity(resource)));
    }

    @PutMapping("/school/{schoolId}")
    public SchoolResource updateSchool(@PathVariable Long schoolId, @Valid @RequestBody SaveSchoolResource resource) {
        return convertToResource(schoolService.updateSchool(schoolId, convertToEntity(resource)));
    }

    @GetMapping("/schools/{schoolId}")
    public SchoolResource getSchoolById(@PathVariable Long schoolId) {
        return convertToResource(schoolService.getSchoolById(schoolId));
    }

    @DeleteMapping("/school/{schoolId}")
    public ResponseEntity<?> deleteSchool(@PathVariable Long schoolId) {
        return schoolService.deleteSchool(schoolId);
    }

    @GetMapping("/schools")
    public Page<SchoolResource> getAllSchools(Pageable pageable) {
        Page<School> schoolPage = schoolService.getAll(pageable);
        List<SchoolResource> resources = schoolPage.getContent()
                .stream().map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }
}
