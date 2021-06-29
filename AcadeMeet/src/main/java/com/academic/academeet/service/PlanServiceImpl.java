package com.academic.academeet.service;

import com.academic.academeet.domain.model.Plan;
import com.academic.academeet.domain.model.University;
import com.academic.academeet.domain.repository.IPlanRepository;
import com.academic.academeet.domain.service.IPlanService;
import com.academic.academeet.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlanServiceImpl implements IPlanService {

    @Autowired
    private IPlanRepository planRepository;

    @Override
    public ArrayList<Plan> getAll() {
        return (ArrayList<Plan>) planRepository.findAll();
    }

    @Override
    public Plan createPlan(Plan plan) {
        return planRepository.save(plan);
    }

    @Override
    public Plan updatePlan(Long planId, Plan planDetails) {
        Plan plan = planRepository.findById(planId)
                .orElseThrow(()->new ResourceNotFoundException("Plan", "Id", planId));
        plan.setName(planDetails.getName());
        return planRepository.save(plan);
    }

    @Override
    public Plan getPlanById(Long planId) {
        return planRepository.findById(planId).
                orElseThrow(() -> new ResourceNotFoundException("Plan", "Id", planId));
    }

    @Override
    public ResponseEntity<?> deletePlan(Long planId) {

        return planRepository.findById(planId)
                .map(plan -> {
                    planRepository.delete(plan);
                    return ResponseEntity.ok().build();
                })
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Plan","Id", planId
                ));
    }
}
