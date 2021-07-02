package com.academic.academeet.domain.repository;

import com.academic.academeet.domain.model.Level;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ILevelRepository extends JpaRepository<Level, Long> {
}
