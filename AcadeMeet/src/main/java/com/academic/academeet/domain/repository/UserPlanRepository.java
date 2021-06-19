package com.academic.academeet.domain.repository;

import com.academic.academeet.domain.model.Plan;
import com.academic.academeet.domain.model.User;
import com.academic.academeet.domain.model.UserPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserPlanRepository extends JpaRepository<UserPlan, Long> {
    List<Plan> findAllByUserId(Long userId);
    List<User> findAllByPlanId(Long planId);
}