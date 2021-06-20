package com.academic.academeet.controller;

import com.academic.academeet.domain.model.User;
import com.academic.academeet.domain.service.PlanService;
import com.academic.academeet.domain.service.UserService;
import com.academic.academeet.resource.SaveUserResource;
import com.academic.academeet.resource.UserResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private PlanService planService;
    @Autowired
    private ModelMapper mapper;

    @GetMapping("/users")
    public List<UserResource> getAllUsers(){
        return userService.getAllUsers()
                .stream()
                .map(
                        user -> mapper.map(user,UserResource.class)
                ).collect(Collectors.toList());
    }

    @PostMapping("/users")
    public UserResource saveUser(@RequestBody SaveUserResource resource){
        User user = convertToEntity(resource);
        return convertToResource(userService.createUser(user));
    }

    @PutMapping("/users/{id}")
    public UserResource updateUser(@PathVariable Long id, @RequestBody SaveUserResource resource){
        User user = convertToEntity(resource);
        return convertToResource(userService.updateUser(id,user));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }

    private User convertToEntity(SaveUserResource resource){
        return mapper.map(resource, User.class);
    }

    private UserResource convertToResource(User entity){
        return mapper.map(entity, UserResource.class);
    }

    @PostMapping("/users/{userid}/plans/{planid}")
    public UserResource assignUserPlan(@PathVariable Long userid, @PathVariable Long planid){
        return convertToResource(userService.assignUserPlan(userid,planid));
    }

    @DeleteMapping("/users/{userid}/plans/{planid}")
    public UserResource unassignUserPlan(@PathVariable Long userid, @PathVariable Long planid){
        return convertToResource(userService.unassignUserPlan(userid,planid));

    }
}