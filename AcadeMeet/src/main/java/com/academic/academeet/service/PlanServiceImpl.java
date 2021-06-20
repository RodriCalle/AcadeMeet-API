package com.academic.academeet.service;

import com.academic.academeet.domain.model.Plan;
import com.academic.academeet.domain.repository.PlanRepository;
import com.academic.academeet.domain.service.PlanService;
import com.academic.academeet.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlanServiceImpl implements PlanService {

    @Autowired
    private PlanRepository planRepository;

    @Override
    public List<Plan> getAllPlansByUserId(Long userid) {
        return null;//planRepository.findAllByUserId(userid);
    }

    @Override
    public ArrayList<Plan> getAllPlans() {
        return (ArrayList<Plan>) planRepository.findAll();
    }

    @Override
    public Plan createPlan(Plan plan) {
        return planRepository.save(plan);
    }

    @Override
    public Plan updatePlan(Long planid, Plan plan) {
        return planRepository.findById(planid).map(
                Plan -> {
                    Plan.setName(plan.getName());
                    Plan.setPrice(plan.getPrice());
                    return planRepository.save(Plan);
                }
        ).orElseThrow(() -> new ResourceNotFoundException("Plan", "Id", plan));
    }

    @Override
    public ResponseEntity<?> deletePlan(Long planid) {

        return planRepository.findById(planid)
                .map(plan -> {
                    planRepository.delete(plan);
                    return ResponseEntity.ok().build();
                })
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Plan","Id", planid
                ));
    }
}
