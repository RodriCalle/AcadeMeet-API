package com.academic.academeet.controller;

import com.academic.academeet.domain.model.School;
import com.academic.academeet.domain.service.SchoolService;
import com.academic.academeet.resource.SaveSchoolResource;
import com.academic.academeet.resource.SchoolResource;
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
    private SchoolService schoolService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping("/schools")
    public Page<SchoolResource> getAllSchools(Pageable pageable) {
        List<SchoolResource> schools = schoolService.getAllSchools(pageable)
                .getContent().stream().map(this::convertToResource).
                collect(Collectors.toList());
        int schoolCount = schools.size();
        return new PageImpl<>(schools, pageable, schoolCount);
    }

    @GetMapping("/schools/{id}")
    public SchoolResource getSchoolById(@PathVariable Long id) {
        return convertToResource(schoolService.getSchoolById(id));
    }

    @PostMapping("/schools")
    public SchoolResource createSchool(@RequestBody SaveSchoolResource resource) {
            return convertToResource(schoolService.createSchool(convertToEntity(resource)));
    }

    @PutMapping("/school/{id}")
    public SchoolResource updateSchool(@PathVariable Long id, @Valid @RequestBody SaveSchoolResource resource) {
        return convertToResource(schoolService.updateSchool(id, convertToEntity(resource)));
    }

    @DeleteMapping("/school/{id}")
    public ResponseEntity<?> deleteSchool(@PathVariable Long id) {
        return schoolService.deleteSchool(id);
    }



    private School convertToEntity(SaveSchoolResource resource) {
        return mapper.map(resource, School.class);
    }
    private SchoolResource convertToResource(School entity) {
        return mapper.map(entity, SchoolResource.class);
    }
}
