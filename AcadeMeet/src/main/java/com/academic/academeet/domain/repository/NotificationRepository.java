package com.academic.academeet.domain.repository;

import com.academic.academeet.domain.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

}
