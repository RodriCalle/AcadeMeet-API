package com.academic.academeet.domain.service;

import com.academic.academeet.domain.model.NotificationType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface INotificationTypeService {
    Page<NotificationType> getAllNotificationTypes(Pageable pageable);
    NotificationType getById(Long id);
    NotificationType saveNotificationType(NotificationType notificationType);
    NotificationType updateNotificationType(Long id, NotificationType notificationType);
    ResponseEntity<?> deleteNotificationType(Long id);
}
