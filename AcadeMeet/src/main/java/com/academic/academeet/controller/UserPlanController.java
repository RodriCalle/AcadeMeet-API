package com.academic.academeet.controller;

import com.academic.academeet.domain.model.User;
import com.academic.academeet.domain.service.IPlanService;
import com.academic.academeet.domain.service.IUserService;
import com.academic.academeet.resource.SaveUserResource;
import com.academic.academeet.resource.UserResource;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserPlanController {
    @Autowired
    private IUserService userService;
    @Autowired
    private IPlanService planService;
    @Autowired
    private ModelMapper mapper;

    private User convertToEntity(SaveUserResource resource){
        return mapper.map(resource, User.class);
    }

    private UserResource convertToResource(User entity){
        return mapper.map(entity, UserResource.class);
    }

    @Operation(summary = "Assign Plan To User"
            , description = "Assign Plan To User with Id"
            , tags = {"user_plans"})
    @PostMapping("/users/{userid}/plans/{planid}")
    public UserResource assignUserPlan(@PathVariable Long userid, @PathVariable Long planid){
        return convertToResource(userService.assignUserPlan(userid,planid));
    }

    @Operation(summary = "Unassign Plan To User"
            , description = "Unassign Plan To User with Id"
            , tags = {"user_plans"})
    @DeleteMapping("/users/{userid}/plans/{planid}")
    public UserResource unassignUserPlan(@PathVariable Long userid, @PathVariable Long planid){
        return convertToResource(userService.unassignUserPlan(userid,planid));
    }
}
