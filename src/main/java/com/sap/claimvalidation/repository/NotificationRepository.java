package com.sap.claimvalidation.repository;

import com.sap.claimvalidation.entities.Notification;
import com.sap.claimvalidation.entities.Result;
import com.sap.claimvalidation.entities.Version;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification,String> {
    List<Notification> findNotificationsByOrganisation(String organization);

}
