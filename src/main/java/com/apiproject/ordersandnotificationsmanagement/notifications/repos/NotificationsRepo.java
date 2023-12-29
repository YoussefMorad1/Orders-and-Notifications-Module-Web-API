package com.apiproject.ordersandnotificationsmanagement.notifications.repos;

import com.apiproject.ordersandnotificationsmanagement.notifications.models.Notification;
import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

@Repository
@Getter
public class NotificationsRepo {
    private final Queue<Notification> notifications;
    private final ArrayList<Notification> sentNotifications;
    NotificationsRepo() {
        notifications = new LinkedList<>();
        sentNotifications = new ArrayList<>();
    }
    public void addNotification(Notification notification) {
        if (notification == null) {
            throw new IllegalArgumentException("Notification cannot be null");
        }
        notifications.add(notification);
    }
    public Notification getNextNotificationAndRemove() {
        Notification notification = notifications.poll();
        if (notification != null) {
            sentNotifications.add(notification);
        }
        return notification;
    }
}
