package com.academic.academeet.domain.repository;

import com.academic.academeet.domain.model.NotificationType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationTypeRepository extends JpaRepository<NotificationType, Long> {

}
