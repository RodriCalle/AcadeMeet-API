package com.academic.academeet.domain.repository;

import com.academic.academeet.domain.model.University;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUniversityRepository extends JpaRepository<University, Long> {
}
