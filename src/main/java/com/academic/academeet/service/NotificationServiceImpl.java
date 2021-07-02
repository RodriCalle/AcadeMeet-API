package com.academic.academeet.service;

import com.academic.academeet.domain.model.Notification;
import com.academic.academeet.domain.model.NotificationType;
import com.academic.academeet.domain.repository.INotificationRepository;
import com.academic.academeet.domain.repository.INotificationTypeRepository;
import com.academic.academeet.domain.service.INotificationService;
import com.academic.academeet.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements INotificationService {

    @Autowired
    private INotificationRepository notificationRepository;
    @Autowired
    private INotificationTypeRepository notificationTypeRepository;

    @Override
    public Page<Notification> getAll(Pageable pageable) {
        return notificationRepository.findAll(pageable);
    }

    @Override
    public Notification getById(Long notificationId) {
        return notificationRepository.findById(notificationId)
                .orElseThrow(() -> new ResourceNotFoundException("Notification", "Id", notificationId));
    }

    @Override
    public Notification createNotification(Long notificationTypeId, Notification notification) {
        return notificationTypeRepository.findById(notificationTypeId).map(notificationType->{
            notification.setNotificationType(notificationType);
            return notificationRepository.save(notification);
        }).orElseThrow(()->new ResourceNotFoundException("NotificationType", "Id", notificationTypeId));
    }

    @Override
    public Notification updateNotification(Long notificationTypeId, Long notificationId,
                                           Notification notificationDetails) {
        NotificationType notificationType = notificationTypeRepository.findById(notificationTypeId)
                .orElseThrow(()->new ResourceNotFoundException("NotificationType", "Id", notificationTypeId));

        return notificationRepository.findById(notificationId).map(notification1 -> {
            notification1.setLinkMeeting(notificationDetails.getLinkMeeting());
            notification1.setContent(notificationDetails.getContent());
            notification1.setLinkResources(notificationDetails.getLinkResources());
            notification1.setNotificationType(notificationType);
            return notificationRepository.save(notification1);
        }).orElseThrow(() -> new ResourceNotFoundException("Notification", "Id", notificationId));
    }

    @Override
    public ResponseEntity<?> deleteNotification(Long notificationId) {
        Notification resource = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new ResourceNotFoundException("Notification", "Id", notificationId));
        notificationRepository.delete(resource);
        return ResponseEntity.ok().build();
    }
}
