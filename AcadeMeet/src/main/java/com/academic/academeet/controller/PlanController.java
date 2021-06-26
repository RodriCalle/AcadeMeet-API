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
    private IPlanService IPlanService;
    @Autowired
    private ModelMapper mapper;

    @GetMapping("/plans")
    public List<PlanResource> getAllPlans(){
        return IPlanService.getAllPlans()
                .stream()
                .map(
                        plan -> mapper.map(plan,PlanResource.class)
                ).collect(Collectors.toList());
    }

    @PostMapping("/plans")
    public PlanResource savePlan(@RequestBody SavePlanResource resource){
        Plan plan = convertToEntity(resource);
        return convertToResource(IPlanService.createPlan(plan));
    }

    @PutMapping("/plans/{id}")
    public PlanResource updatePlan(@PathVariable Long id,@RequestBody SavePlanResource resource){
        Plan plan = convertToEntity(resource);
        return convertToResource(IPlanService.updatePlan(id, plan));
    }

    @DeleteMapping("/plans/{id}")
    public ResponseEntity<?> deletePlan(@PathVariable Long id){
        return IPlanService.deletePlan(id);
    }

    private Plan convertToEntity(SavePlanResource resource){
        return mapper.map(resource, Plan.class);
    }

    private PlanResource convertToResource(Plan entity){
        return mapper.map(entity, PlanResource.class);
    }
}
