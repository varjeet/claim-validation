package com.sap.claimvalidation.controller;

import com.sap.claimvalidation.dtos.ClaimResponseDto;
import com.sap.claimvalidation.entities.Claim;
import com.sap.claimvalidation.entities.Notification;
import com.sap.claimvalidation.service.NotificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/claim/getNotification")
    public ResponseEntity<List<Notification>> getNotificationByOrganization(@RequestParam(value = "organisation") String organisation){
        return new ResponseEntity<>(notificationService.getNotificationByOrganization(organisation),HttpStatus.OK);
    }
}
