package com.academic.academeet.service;

import com.academic.academeet.domain.model.Notification;
import com.academic.academeet.domain.repository.NotificationRepository;
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
    private NotificationRepository notificationRepository;

    @Override
    public Page<Notification> getAllNotifications(Pageable pageable) {
        return notificationRepository.findAll(pageable);
    }

    @Override
    public Notification getById(Long id) {
        return notificationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Notification", "Id", id));
    }

    @Override
    public Notification saveNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    @Override
    public Notification updateNotification(Long id, Notification notification) {
        Notification resource = notificationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Notification", "Id", id));

        resource.setLinkMeeting(notification.getLinkMeeting());
        resource.setLinkResources(notification.getLinkResources());
        resource.setContent(notification.getContent());

        return notificationRepository.save(resource);
    }

    @Override
    public ResponseEntity<?> deleteNotification(Long id) {
        Notification resource = notificationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Notification", "Id", id));

        notificationRepository.delete(resource);
        return ResponseEntity.ok().build();
    }
}
