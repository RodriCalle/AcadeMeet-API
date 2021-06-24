package com.academic.academeet.service;

import com.academic.academeet.domain.model.NotificationType;
import com.academic.academeet.domain.repository.NotificationTypeRepository;
import com.academic.academeet.domain.service.NotificationService;
import com.academic.academeet.domain.service.NotificationTypeService;
import com.academic.academeet.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class NotificationTypeServiceImpl implements NotificationTypeService {

    @Autowired
    private NotificationTypeRepository notificationTypeRepository;

    @Override
    public Page<NotificationType> getAllNotificationTypes(Pageable pageable) {
        return notificationTypeRepository.findAll(pageable);
    }

    @Override
    public NotificationType getById(Long id) {
        return notificationTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Notification Type", "Id", id));
    }

    @Override
    public NotificationType saveNotificationType(NotificationType notificationType) {
        return notificationTypeRepository.save(notificationType);
    }

    @Override
    public NotificationType updateNotificationType(Long id, NotificationType notificationType) {
       NotificationType resource = notificationTypeRepository.findById(id)
               .orElseThrow(() -> new ResourceNotFoundException("Notification Type", "Id", id));

       resource.setDescription(notificationType.getDescription());
       return notificationTypeRepository.save(resource);
    }

    @Override
    public ResponseEntity<?> deleteNotificationType(Long id) {
        NotificationType resource = notificationTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Notification Type", "Id", id));

        notificationTypeRepository.delete(resource);
        return ResponseEntity.ok().build();
    }
}
