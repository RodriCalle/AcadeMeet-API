package com.academic.academeet.controller;

import com.academic.academeet.domain.model.User;
import com.academic.academeet.domain.service.IPlanService;
import com.academic.academeet.domain.service.IUserService;
import com.academic.academeet.resource.SaveUserResource;
import com.academic.academeet.resource.UserResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class UserController {
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

    @GetMapping("/users")
    public List<UserResource> getAllUsers(){
        return userService.getAll()
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
}
