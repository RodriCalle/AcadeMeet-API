package com.academic.academeet.service;

import com.academic.academeet.domain.model.UserPlan;
import com.academic.academeet.domain.repository.UserPlanRepository;
import com.academic.academeet.domain.service.UserPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserPlanServiceImpl implements UserPlanService {

    @Autowired
    private UserPlanRepository userPlanRepository;

    @Override
    public List<UserPlan> getAllByUserId(Long userid) {
        return null;
    }

    @Override
    public List<UserPlan> getAllByPlanId(Long planid) {
        return null;
    }

    @Override
    public List<UserPlan> getAllUserPlans() {
        return null;
    }

    @Override
    public UserPlan createUserPlan(Long userid, Long planid, UserPlan userplan) {
        return null;
    }

    @Override
    public ResponseEntity<?> deleteUserPlan(Long userid, Long planid) {
        return null;
    }
}
