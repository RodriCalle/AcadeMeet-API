package com.academic.academeet.domain.repository;

import com.academic.academeet.domain.model.Carrer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICarrerRepository extends JpaRepository<Carrer,Long> {
}
