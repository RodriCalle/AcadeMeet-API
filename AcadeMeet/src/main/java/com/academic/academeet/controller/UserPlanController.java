package com.academic.academeet.controller;

import com.academic.academeet.domain.model.UserPlan;
import com.academic.academeet.domain.service.UserPlanService;
import com.academic.academeet.resource.SavePlanResource;
import com.academic.academeet.resource.UserPlanResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class UserPlanController {
    @Autowired
    private UserPlanService userPlanService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping("/userplans")
    public List<UserPlanResource> getAllUserPlansByUserId(Long userid) {
        List<UserPlan> userPlans = userPlanService.getAllByUserId(userid);
        return userPlans.stream().map(this::convertToResource).collect(Collectors.toList());
    }
    /*
    @GetMapping("/userplans")
    public List<UserPlanResource> getAllUserPlansByPlanId(Long planid) {
        List<UserPlan> userPlans = userPlanService.getAllByPlanId(planid);
        return userPlans.stream().map(this::convertToResource).collect(Collectors.toList());
    }
    
     */

    @DeleteMapping("/userplans/{userplanid}")
    public ResponseEntity<?> deleteUserPlan(Long userid,@PathVariable Long userplanid){
        return userPlanService.deleteUserPlan(userid,userplanid);
    }

    private UserPlan convertToEntity(SavePlanResource resource){
        return mapper.map(resource, UserPlan.class);
    }

    private UserPlanResource convertToResource(UserPlan entity){
        return mapper.map(entity, UserPlanResource.class);
    }
}
