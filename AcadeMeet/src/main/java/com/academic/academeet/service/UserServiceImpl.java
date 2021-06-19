package com.academic.academeet.service;

import com.academic.academeet.domain.model.User;
import com.academic.academeet.domain.repository.UserRepository;
import com.academic.academeet.domain.service.UserService;
import com.academic.academeet.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUsersByPlanId(Long planid) {
        return userRepository.findAllByPlanId(planid);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long userid, User user) {
        return userRepository.findById(userid).map(
                User -> {
                    User.setFirst_name(user.getFirst_name());
                    User.setLast_name(user.getLast_name());
                    User.setPassword(user.getPassword());
                    User.setMail(user.getMail());
                    return userRepository.save(User);
                }
        ).orElseThrow(() -> new ResourceNotFoundException("User", "Id", user));
    }

    @Override
    public ResponseEntity<?> deleteUser(Long userid) {
        return userRepository.findById(userid)
                .map(plan -> {
                    userRepository.delete(plan);
                    return ResponseEntity.ok().build();
                })
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User","Id", userid
                ));
    }
}
