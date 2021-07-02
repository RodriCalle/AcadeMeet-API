package com.academic.academeet.domain.service;

import com.academic.academeet.domain.model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IUserService {
    User createUser(User user);
    User updateUser(Long userId, User userDetails);
    User getUserById(Long userId);
    ResponseEntity<?> deleteUser(Long userId);
    List<User> getAll();

    User assignUserPlan(Long userId, Long planId);
    User unassignUserPlan(Long userId, Long planId);
}
