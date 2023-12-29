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
    @GetMapping("/getAllInQueue")
    public Queue<Notification> getAllNotifications() {
        return notificationsService.getNotificationsQueue();
    }

    // This Endpoint isn't required in the project's requirements
    // Since it only asks to remove the notification from the queue
    // And no need to access them later
//    @GetMapping("/getAllSent")
//    public ArrayList<Notification> getSentNotifications() {
//        return notificationsService.getSentNotifications();
//    }
}
