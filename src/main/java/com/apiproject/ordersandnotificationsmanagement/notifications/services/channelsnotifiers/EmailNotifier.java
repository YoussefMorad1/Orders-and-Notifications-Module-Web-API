package com.apiproject.ordersandnotificationsmanagement.notifications.services.channelsnotifiers;

import com.apiproject.ordersandnotificationsmanagement.notifications.enums.NotificationChannel;
import com.apiproject.ordersandnotificationsmanagement.notifications.models.Notification;
import com.apiproject.ordersandnotificationsmanagement.notifications.services.NotificationsService;


public class EmailNotifier extends Notifier {
    public EmailNotifier(NotificationsService notificationsService) {
        super(notificationsService);
    }
    @Override
    public void sendNotification(Notification notification) {
        notification.setChannel(NotificationChannel.EMAIL);
        notificationsService.addNotification(notification);
    }
}
