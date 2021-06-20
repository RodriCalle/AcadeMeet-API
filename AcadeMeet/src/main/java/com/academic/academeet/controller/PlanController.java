package com.academic.academeet.controller;

import com.academic.academeet.domain.model.Plan;
import com.academic.academeet.domain.service.PlanService;
import com.academic.academeet.resource.PlanResource;
import com.academic.academeet.resource.SavePlanResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class PlanController {
    @Autowired
    private PlanService planService;
    @Autowired
    private ModelMapper mapper;

    @GetMapping("/plans")
    public List<PlanResource> getAllPlans(){
        return planService.getAllPlans()
                .stream()
                .map(
                        plan -> mapper.map(plan,PlanResource.class)
                ).collect(Collectors.toList());
    }

    @PostMapping("/plans")
    public PlanResource savePlan(@RequestBody SavePlanResource resource){
        Plan plan = convertToEntity(resource);
        return convertToResource(planService.createPlan(plan));
    }

    @PutMapping("/plans/{id}")
    public PlanResource updatePlan(@PathVariable Long id,@RequestBody SavePlanResource resource){
        Plan plan = convertToEntity(resource);
        return convertToResource(planService.updatePlan(id, plan));
    }

    @DeleteMapping("/plans/{id}")
    public ResponseEntity<?> deletePlan(@PathVariable Long id){
        return planService.deletePlan(id);
    }

    private Plan convertToEntity(SavePlanResource resource){
        return mapper.map(resource, Plan.class);
    }

    private PlanResource convertToResource(Plan entity){
        return mapper.map(entity, PlanResource.class);
    }
}
