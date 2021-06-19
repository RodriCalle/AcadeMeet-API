package com.academic.academeet.domain.repository;

import com.academic.academeet.domain.model.School;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolRepository extends JpaRepository<School, Long> {
}
