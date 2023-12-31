package com.apiproject.ordersandnotificationsmanagement.notifications.services.channelsnotifiers;
import com.apiproject.ordersandnotificationsmanagement.notifications.models.Notification;

public abstract class BaseNotifierDecorator extends Notifier {
    private Notifier wrappeeNotifier;
    public BaseNotifierDecorator(Notifier wrappeeNotifier) {
        super(wrappeeNotifier.notificationsService);
        this.wrappeeNotifier = wrappeeNotifier;
    }
    @Override
    public void sendNotification(Notification notification) {
        wrappeeNotifier.sendNotification(notification);
    }
}
