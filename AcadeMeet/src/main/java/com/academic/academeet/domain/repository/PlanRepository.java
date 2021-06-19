package com.academic.academeet.domain.repository;

import com.academic.academeet.domain.model.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanRepository extends JpaRepository<Plan,Long> {
    //List<Plan> findAllByUserId(Long userid);
}
