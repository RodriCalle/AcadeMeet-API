package com.academic.academeet.domain.service;

import com.academic.academeet.domain.model.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface NotificationService {
    Page<Notification> getAllNotifications(Pageable pageable);
    Notification getById(Long id);
    Notification saveNotification(Long notificationTypeId, Notification notification);
    Notification updateNotification(Long id, Notification notification);
    ResponseEntity<?> deleteNotification(Long id);
}
