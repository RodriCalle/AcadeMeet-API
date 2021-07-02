package com.academic.academeet.domain.service;

import com.academic.academeet.domain.model.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface INotificationService {
    Notification createNotification(Long notificationTypeId, Notification notification);
    Notification updateNotification(Long notificationTypeId, Long notificationId, Notification notificationDetails);
    Notification getById(Long notificationId);
    ResponseEntity<?> deleteNotification(Long notificationId);
    Page<Notification> getAll(Pageable pageable);
}
