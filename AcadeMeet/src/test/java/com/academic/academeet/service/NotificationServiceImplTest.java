package com.academic.academeet.service;

import com.academic.academeet.domain.model.Notification;
import com.academic.academeet.domain.model.NotificationType;
import com.academic.academeet.domain.repository.INotificationRepository;
import com.academic.academeet.domain.repository.INotificationTypeRepository;
import com.academic.academeet.domain.service.INotificationService;
import com.academic.academeet.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class NotificationServiceImplTest {

    @MockBean
    private INotificationRepository notificationRepository;
    @MockBean
    private INotificationTypeRepository notificationTypeRepository;

    @Autowired
    private INotificationService notificationService;


    @TestConfiguration
    static class NotificationServiceTestConfiguration {
        @Bean
        public INotificationService notificationService() { return new NotificationServiceImpl(); }
    }

    @Test
    void whenCreateNotificationWithIdsValidThenReturnsNotification() {
        // Arrange
        long notificationTypeId = 1;
        Notification notification = new Notification();
        NotificationType notificationType = new NotificationType();


        when(notificationTypeRepository.findById(notificationTypeId)).thenReturn(Optional.of(notificationType));
        when(notificationRepository.save(notification)).thenReturn(notification);

        // Action
        Notification result = notificationService.createNotification(notificationTypeId, notification);

        // Assert
        assertThat(result).isEqualTo(notification);
    }

    @Test
    void whenCreateNotificationWitNotificationTypeIdInvalidThenReturnsResourceNotFoundException() {
        // Arrange
        long notificationTypeId = 1;
        Notification notification = new Notification();
        NotificationType notificationType = new NotificationType();

        when(notificationTypeRepository.findById(notificationTypeId)).thenReturn(Optional.empty());
        when(notificationRepository.save(notification)).thenReturn(notification);

        // Action
        Throwable exception = catchThrowable(() -> {
            Notification result = notificationService.createNotification(notificationTypeId, notification);
        });

        // Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Resource NotificationType not found for Id with value 1");
    }

    @Test
    void whenUpdateEventWithIdsValidThenReturnsEvent() {
        // Arrange
        long notificationTypeId = 1;
        NotificationType notificationType = new NotificationType();
        long notificationId = 1;
        Notification notification = new Notification();
        notification.setNotificationType(notificationType);

        when(notificationTypeRepository.findById(notificationTypeId)).thenReturn(Optional.of(notificationType));
        when(notificationRepository.findById(notificationId)).thenReturn(Optional.of(notification));
        when(notificationRepository.save(notification)).thenReturn(notification);

        // Act
        Notification result = notificationService.updateNotification(notificationTypeId, notificationId, notification);

        // Assert
        assertThat(result).isEqualTo(notification);
    }


    @Test
    void whenUpdateNotificationWithNotificationTypeIdInvalidThenReturnsResourceNotFoundException() {
        // Arrange
        long notificationTypeId = 1;
        NotificationType notificationType = new NotificationType();
        long notificationId = 1;
        Notification notification = new Notification();
        notification.setNotificationType(notificationType);

        when(notificationTypeRepository.findById(notificationTypeId)).thenReturn(Optional.empty());
        when(notificationRepository.findById(notificationId)).thenReturn(Optional.of(notification));
        when(notificationRepository.save(notification)).thenReturn(notification);



        // Action
        Throwable exception = catchThrowable(() -> {
            Notification result = notificationService.updateNotification(notificationTypeId, notificationId, notification);
        });

        // Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Resource NotificationType not found for Id with value 1");
    }


}
