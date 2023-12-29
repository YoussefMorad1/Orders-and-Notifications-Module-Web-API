package com.apiproject.ordersandnotificationsmanagement.notifications.services;

import com.apiproject.ordersandnotificationsmanagement.notifications.enums.NotificationChannel;
import com.apiproject.ordersandnotificationsmanagement.notifications.models.Notification;
import com.apiproject.ordersandnotificationsmanagement.notifications.repos.NotificationsRepo;
import com.apiproject.ordersandnotificationsmanagement.notifications.services.messagetemplates.SetOrderMessageTemplate;
import com.apiproject.ordersandnotificationsmanagement.notifications.services.messagetemplates.ShipOrderMessageTemplate;
import com.apiproject.ordersandnotificationsmanagement.orders.models.SimpleOrder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Queue;

@Service
@AllArgsConstructor
public class NotificationsService {
    NotificationsRepo notificationsRepo;
    NotificationsStatistics notificationsStatistics;
    SetOrderMessageTemplate setOrderMessageTemplateService;
    ShipOrderMessageTemplate shipOrderMessageTemplateService;
    public void sendPlaceOrderNotification(SimpleOrder order) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }
        Notification smsNotification = setOrderMessageTemplateService.getNotification(
                order.getUsername(),
                order.getOrderID(),
                order.getAccount().getCustomerInfo().getLanguage(),
                NotificationChannel.SMS
        );
        Notification emailNotification = setOrderMessageTemplateService.getNotification(
                order.getUsername(),
                order.getOrderID(),
                order.getAccount().getCustomerInfo().getLanguage(),
                NotificationChannel.EMAIL
        );
        if (smsNotification != null) {
            notificationsRepo.addNotification(smsNotification);
        }
        if (emailNotification != null) {
            notificationsRepo.addNotification(emailNotification);
        }
    }

    public void sendShipOrderNotification(SimpleOrder order) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }
        Notification emailNotification = shipOrderMessageTemplateService.getNotification(
                order.getUsername(),
                order.getOrderID(),
                order.getAccount().getCustomerInfo().getLanguage(),
                NotificationChannel.EMAIL
        );
        Notification smsNotification = shipOrderMessageTemplateService.getNotification(
                order.getUsername(),
                order.getOrderID(),
                order.getAccount().getCustomerInfo().getLanguage(),
                NotificationChannel.SMS
        );
        if (emailNotification != null) {
            notificationsRepo.addNotification(emailNotification);
        }
        if (smsNotification != null) {
            notificationsRepo.addNotification(smsNotification);
        }
    }

    public Queue<Notification> getNotificationsQueue() {
        return notificationsRepo.getNotifications();
    }

    public ArrayList<Notification> getSentNotifications() {
        return notificationsRepo.getSentNotifications();
    }
}
