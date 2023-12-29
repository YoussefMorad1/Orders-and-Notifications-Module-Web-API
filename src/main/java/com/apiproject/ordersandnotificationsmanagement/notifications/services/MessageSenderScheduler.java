package com.apiproject.ordersandnotificationsmanagement.notifications.services;

import com.apiproject.ordersandnotificationsmanagement.notifications.models.Notification;
import com.apiproject.ordersandnotificationsmanagement.notifications.repos.NotificationsRepo;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class MessageSenderScheduler {
    private final NotificationsRepo notificationsRepo;
    private final NotificationsStatistics notificationsStatistics;
    @Scheduled(fixedDelay = 20 * 1000, initialDelay = 20 * 1000) // Run every 20 seconds
    public void sendNotifications() {
        Notification notification = notificationsRepo.getNextNotificationAndRemove();
        if (notification == null)
            return;
        notificationsStatistics.analyze(notification); // Analyze notification that are sent successfully
    }
}
