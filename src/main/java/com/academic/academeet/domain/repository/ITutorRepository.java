package com.academic.academeet.domain.repository;

import com.academic.academeet.domain.model.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITutorRepository extends JpaRepository<Tutor, Long> {
}
