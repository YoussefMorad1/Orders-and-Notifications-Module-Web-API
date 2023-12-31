package com.apiproject.ordersandnotificationsmanagement.notifications.services.channelsnotifiers;

import com.apiproject.ordersandnotificationsmanagement.notifications.enums.NotificationChannel;
import com.apiproject.ordersandnotificationsmanagement.notifications.models.Notification;

public class SmsNotifierDecorator extends BaseNotifierDecorator{
    public SmsNotifierDecorator(Notifier wrappeeNotifier) {
        super(wrappeeNotifier);
    }
    @Override
    public void sendNotification(Notification notification) {
        super.sendNotification(notification);
        // Create a new notification object to avoid modifying the original notification object
        // Send the notification with the SMS channel to the NotificationsRepo
        notification = new Notification(notification);
        notification.setChannel(NotificationChannel.SMS);
        notificationsService.addNotification(notification);
    }
}
