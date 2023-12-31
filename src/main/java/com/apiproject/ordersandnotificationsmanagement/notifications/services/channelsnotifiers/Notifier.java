package com.apiproject.ordersandnotificationsmanagement.notifications.services.channelsnotifiers;

import com.apiproject.ordersandnotificationsmanagement.notifications.models.Notification;
import com.apiproject.ordersandnotificationsmanagement.notifications.services.NotificationsService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class Notifier {
    NotificationsService notificationsService;
    public abstract void sendNotification(Notification notification);
}
