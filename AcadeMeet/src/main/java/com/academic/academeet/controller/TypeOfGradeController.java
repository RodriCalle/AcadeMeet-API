package com.academic.academeet.controller;

import com.academic.academeet.domain.model.TypeOfGrade;
import com.academic.academeet.domain.service.ITypeOfGradeService;
import com.academic.academeet.resource.SaveTypeOfGradeResource;
import com.academic.academeet.resource.TypeOfGradeResource;
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
    private ITypeOfGradeService ITypeOfGradeService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping("/type_of_grades")
    public Page<TypeOfGradeResource> getTypeOfGrades(Pageable pageable) {
        List<TypeOfGradeResource> schools = ITypeOfGradeService.getAllTypeOfGrades(pageable)
                .getContent().stream().map(this::convertToResource).
                        collect(Collectors.toList());
        int schoolCount = schools.size();
        return new PageImpl<>(schools, pageable, schoolCount);
    }

    @GetMapping("/type_of_grades/{id}")
    public TypeOfGradeResource getTypeOfGradeById(@PathVariable Long id) {
        return convertToResource(ITypeOfGradeService.getTypeOfGradeById(id));

    }

    @PostMapping("/type_of_grades")
    public TypeOfGradeResource createTypeOfGrade(@RequestBody SaveTypeOfGradeResource resource) {
        return convertToResource(ITypeOfGradeService.saveTypeOfGrade(convertToEntity(resource)));

    }


    @PutMapping("/type_of_grade/{id}")
    public TypeOfGradeResource updateTypeOfGrade(@PathVariable Long id, @Valid @RequestBody SaveTypeOfGradeResource resource) {
        return convertToResource(ITypeOfGradeService.updateTypeOfGrade(id, convertToEntity(resource)));

    }

    @DeleteMapping("/type_of_grade/{id}")
    public ResponseEntity<?> deleteTypeOfGrade(@PathVariable Long id) {
        return ITypeOfGradeService.deleteTypeOfGrade(id);
    }


    private TypeOfGrade convertToEntity(SaveTypeOfGradeResource resource) {
        return mapper.map(resource, TypeOfGrade.class);
    }
    private TypeOfGradeResource convertToResource(TypeOfGrade entity) {
        return mapper.map(entity, TypeOfGradeResource.class);
    }
}
