package com.academic.academeet.domain.service;

import com.academic.academeet.domain.model.Plan;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IPlanService {
    Plan createPlan(Plan plan);
    Plan updatePlan(Long planId, Plan planDetails);
    Plan getPlanById(Long planId);
    ResponseEntity<?> deletePlan(Long planId);
    List<Plan> getAll();
}
