package com.academic.academeet.domain.service;

import com.academic.academeet.domain.model.UserPlan;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserPlanService {
    List<UserPlan> getAllByUserId(Long userid);
    List<UserPlan> getAllByPlanId(Long planid);
    List<UserPlan> getAllUserPlans();
    UserPlan createUserPlan(Long userid, Long planid, UserPlan userplan);
    ResponseEntity<?> deleteUserPlan(Long userid,Long planid);
}
