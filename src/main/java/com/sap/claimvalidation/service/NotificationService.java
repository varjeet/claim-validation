package com.sap.claimvalidation.service;

import com.sap.claimvalidation.entities.Item;
import com.sap.claimvalidation.entities.Notification;
import com.sap.claimvalidation.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;


    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;

    }
    public Notification addNotification(Notification notification)
    {
        Notification notificationResponse= this.notificationRepository.save(notification);
        return notificationResponse;

    }

    public List<Notification> getAllNotifications(){
        return this.notificationRepository.findAll();
    }

    public void deleteClaim(String notificationId)
    {

        this.notificationRepository.deleteById(notificationId);

    }


}
