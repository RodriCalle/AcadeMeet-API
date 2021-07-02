package com.academic.academeet.controller;

import com.academic.academeet.domain.model.Plan;
import com.academic.academeet.domain.service.IPlanService;
import com.academic.academeet.resource.PlanResource;
import com.academic.academeet.resource.SavePlanResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class PlanController {
    @Autowired
    private IPlanService planService;
    @Autowired
    private ModelMapper mapper;

    private Plan convertToEntity(SavePlanResource resource){
        return mapper.map(resource, Plan.class);
    }
    private PlanResource convertToResource(Plan entity){
        return mapper.map(entity, PlanResource.class);
    }

    @PostMapping("/plans")
    public PlanResource createPlan(@RequestBody SavePlanResource resource){
        Plan plan = convertToEntity(resource);
        return convertToResource(planService.createPlan(plan));
    }

    @PutMapping("/plans/{planId}")
    public PlanResource updatePlan(@PathVariable Long planId,@RequestBody SavePlanResource resource){
        Plan plan = convertToEntity(resource);
        return convertToResource(planService.updatePlan(planId, plan));
    }

    @GetMapping("/plans/{planId}")
    public PlanResource getPlanById(@PathVariable Long planId){
        return convertToResource(planService.getPlanById(planId));
    }

    @DeleteMapping("/plans/{planId}")
    public ResponseEntity<?> deletePlan(@PathVariable Long planId){
        return planService.deletePlan(planId);
    }

    @GetMapping("/plans")
    public List<PlanResource> getAllPlans(){
        return planService.getAll()
                .stream()
                .map(
                        plan -> mapper.map(plan,PlanResource.class)
                ).collect(Collectors.toList());
    }
}
