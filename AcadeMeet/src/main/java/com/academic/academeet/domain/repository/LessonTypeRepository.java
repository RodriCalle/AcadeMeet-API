package com.academic.academeet.domain.repository;

import com.academic.academeet.domain.model.LessonType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonTypeRepository extends JpaRepository<LessonType, Long> {
}
