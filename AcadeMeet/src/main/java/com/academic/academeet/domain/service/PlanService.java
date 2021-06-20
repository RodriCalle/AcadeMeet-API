package com.academic.academeet.domain.service;

import com.academic.academeet.domain.model.Plan;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PlanService {
    List<Plan> getAllPlansByUserId(Long id);
    List<Plan> getAllPlans();
    Plan createPlan(Plan plan);
    Plan updatePlan(Long planid,Plan plan);
    ResponseEntity<?> deletePlan(Long planid);
}
