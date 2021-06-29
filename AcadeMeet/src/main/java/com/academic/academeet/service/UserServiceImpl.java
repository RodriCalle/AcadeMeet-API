package com.academic.academeet.service;

import com.academic.academeet.domain.model.Plan;
import com.academic.academeet.domain.model.User;
import com.academic.academeet.domain.repository.IPlanRepository;
import com.academic.academeet.domain.repository.IUserRepository;
import com.academic.academeet.domain.service.IUserService;
import com.academic.academeet.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IPlanRepository planRepository;

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long userId, User userDetails) {
        return userRepository.findById(userId).map(
                User -> {
                    User.setFirst_name(userDetails.getFirst_name());
                    User.setLast_name(userDetails.getLast_name());
                    User.setPassword(userDetails.getPassword());
                    User.setMail(userDetails.getMail());
                    return userRepository.save(User);
                }
        ).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
    }

    @Override
    public ResponseEntity<?> deleteUser(Long userId) {
        return userRepository.findById(userId)
                .map(plan -> {
                    userRepository.delete(plan);
                    return ResponseEntity.ok().build();
                })
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User","Id", userId
                ));
    }

    @Override
    public User assignUserPlan(Long userId, Long planId) {
        Plan plan = planRepository.findById(planId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Plan", "Id", planId));
        return userRepository.findById(userId).map(
                user -> userRepository.save(user.tagWith(plan)))
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User", "Id", userId));
    }

    @Override
    public User unassignUserPlan(Long userId, Long planId) {
        Plan plan = planRepository.findById(planId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Plan", "Id", planId));
        return userRepository.findById(userId).map(
                user -> userRepository.save(user.untagWith(plan)))
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User", "Id", userId));
    }
}
