package com.apiproject.ordersandnotificationsmanagement.notifications.services;

import com.apiproject.ordersandnotificationsmanagement.notifications.enums.NotificationChannel;
import com.apiproject.ordersandnotificationsmanagement.notifications.enums.NotificationReason;
import com.apiproject.ordersandnotificationsmanagement.notifications.models.Notification;
import com.apiproject.ordersandnotificationsmanagement.notifications.repos.NotificationsRepo;
import com.apiproject.ordersandnotificationsmanagement.notifications.services.channelsnotifiers.EmailNotifier;
import com.apiproject.ordersandnotificationsmanagement.notifications.services.channelsnotifiers.Notifier;
import com.apiproject.ordersandnotificationsmanagement.notifications.services.channelsnotifiers.SmsNotifierDecorator;
import com.apiproject.ordersandnotificationsmanagement.notifications.services.messagetemplates.MessageTemplate;
import com.apiproject.ordersandnotificationsmanagement.notifications.services.messagetemplates.MessageTemplateFactory;
import com.apiproject.ordersandnotificationsmanagement.orders.models.SimpleOrder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Queue;

@Service
@AllArgsConstructor
public class NotificationsService {
    NotificationsStatistics notificationsStatistics;
    NotificationsRepo notificationsRepo;
    MessageTemplateFactory messageTemplateFactory;
    public void sendNotification(SimpleOrder order, NotificationReason notificationReason) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }
        Notifier notifier = new EmailNotifier(this);
        MessageTemplate messageTemplate = messageTemplateFactory.getMessageTemplate(notificationReason);
        if (messageTemplate.getAvailableChannels().contains(NotificationChannel.SMS)) {
            notifier = new SmsNotifierDecorator(notifier);
        }
        notifier.sendNotification(messageTemplate.getNotification(
                order.getUsername(),
                order.getOrderID(),
                order.getAccount().getCustomerInfo().getLanguage()
        ));
    }
    public Queue<Notification> getNotificationsQueue() {
        return notificationsRepo.getNotifications();
    }

    public void addNotification(Notification notification) {
        if (notification == null) {
            throw new IllegalArgumentException("Notification cannot be null");
        }
        notificationsRepo.addNotification(notification);
    }
}
