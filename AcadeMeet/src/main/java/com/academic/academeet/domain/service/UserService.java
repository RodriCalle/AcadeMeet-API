package com.academic.academeet.domain.service;

import com.academic.academeet.domain.model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    List<User> getAllUsersByPlanId(Long planid);
    List<User> getAllUsers();
    User createUser(User user);
    User updateUser(Long userid, User user);
    ResponseEntity<?> deleteUser(Long userid);
}
