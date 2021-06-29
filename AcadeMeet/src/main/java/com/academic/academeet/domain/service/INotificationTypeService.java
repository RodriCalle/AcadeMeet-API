package com.academic.academeet.domain.service;

import com.academic.academeet.domain.model.NotificationType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface INotificationTypeService {
    NotificationType createNotificationType(NotificationType notificationType);
    NotificationType updateNotificationType(Long notificationTypeId, NotificationType notificationTypeDetails);
    NotificationType getById(Long notificationTypeId);
    ResponseEntity<?> deleteNotificationType(Long notificationTypeId);
    Page<NotificationType> getAll(Pageable pageable);

}
