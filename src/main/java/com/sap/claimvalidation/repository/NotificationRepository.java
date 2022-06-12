package com.sap.claimvalidation.repository;

import com.sap.claimvalidation.entities.Notification;
import com.sap.claimvalidation.entities.Result;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification,String> {


}
