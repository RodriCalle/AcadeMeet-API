package com.academic.academeet.domain.repository;

import com.academic.academeet.domain.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ILessonRepository extends JpaRepository<Lesson, Long> {
}
