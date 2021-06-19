package com.academic.academeet.service;

import com.academic.academeet.domain.model.UserPlan;
import com.academic.academeet.domain.repository.PlanRepository;
import com.academic.academeet.domain.repository.UserPlanRepository;
import com.academic.academeet.domain.repository.UserRepository;
import com.academic.academeet.domain.service.UserPlanService;
import com.academic.academeet.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserPlanServiceImpl implements UserPlanService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private UserPlanRepository userPlanRepository;

    @Override
    public List<UserPlan> getAllByUserId(Long userid) {
        return userPlanRepository.findAllByUserId(userid);
    }

    @Override
    public List<UserPlan> getAllByPlanId(Long planid) {
        return userPlanRepository.findAllByPlanId(planid);
    }

    @Override
    public List<UserPlan> getAllUserPlans() {
        return userPlanRepository.findAll();
    }

    @Override
    public UserPlan createUserPlan(Long userid, Long planid, UserPlan userplan) {
        return userRepository.findById(userid).map(user -> planRepository.findById(planid).map(plan -> {
            userplan.setUser(user);
            userplan.setPlan(plan);
            return userPlanRepository.save(userplan);
        }).orElseThrow(() -> new ResourceNotFoundException(
                "User","Id",userid
        ))).orElseThrow(() -> new ResourceNotFoundException(
                "Plan","Id", planid
        ));
    }

    @Override
    public ResponseEntity<?> deleteUserPlan(Long userid, Long id) {
        if(!userRepository.existsById(userid))
            throw new ResourceNotFoundException(
                    "Offer","Id", userid
            );
        return userPlanRepository.findById(id).map(userPlan -> {
            userPlanRepository.delete(userPlan);
            return ResponseEntity.ok().build();
        }).orElseThrow(()->new ResourceNotFoundException(
                "UserPlan","Id",id
        ));
    }
}
