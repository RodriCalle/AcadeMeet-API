package com.academic.academeet.domain.repository;

import com.academic.academeet.domain.model.School;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISchoolRepository extends JpaRepository<School, Long> {
}
