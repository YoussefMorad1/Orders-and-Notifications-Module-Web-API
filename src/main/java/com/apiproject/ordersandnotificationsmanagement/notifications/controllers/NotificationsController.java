package com.apiproject.ordersandnotificationsmanagement.notifications.controllers;

import com.apiproject.ordersandnotificationsmanagement.notifications.models.Notification;
import com.apiproject.ordersandnotificationsmanagement.notifications.services.NotificationsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Queue;

@RestController
@RequestMapping("/notifications")
@AllArgsConstructor
public class NotificationsController {
    NotificationsService notificationsService;
    @GetMapping("/getInQueue")
    public Queue<Notification> getAllNotifications() {
        return notificationsService.getNotificationsQueue();
    }
}
