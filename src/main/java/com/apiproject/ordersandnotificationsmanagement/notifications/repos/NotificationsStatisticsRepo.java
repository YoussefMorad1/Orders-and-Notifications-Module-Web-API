package com.apiproject.ordersandnotificationsmanagement.notifications.repos;

import com.apiproject.ordersandnotificationsmanagement.notifications.enums.NotificationChannel;
import com.apiproject.ordersandnotificationsmanagement.notifications.models.Notification;
import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
@Getter
public class NotificationsStatisticsRepo {
    private HashMap<String, Integer> mailFrequency;
    private HashMap<String, Integer> smsFrequency;
    private HashMap<String, Integer> mostUsedMessageTemplate;

    NotificationsStatisticsRepo() {
        mailFrequency = new HashMap<>();
        mostUsedMessageTemplate = new HashMap<>();
        smsFrequency = new HashMap<>();
    }

    public void addNotification(Notification notification) {
        // Analyze channels, to know most used channels
        if (notification.getChannel() == NotificationChannel.EMAIL) {
            analyzeEmail(notification);
        } else if (notification.getChannel() == NotificationChannel.SMS) {
            analyzeSMS(notification);
        }
        // Analyze message template, to know most used template
        analyzeNotificationTemplate(notification);
    }

    private void analyzeNotificationTemplate(Notification notification) {
        String templateName = notification.getNotificationTemplate().toString();
        mostUsedMessageTemplate.put(templateName, mostUsedMessageTemplate.getOrDefault(templateName, 0) + 1);
    }

    private void analyzeEmail(Notification notification) {
        String phoneNumber = notification.getAccount().getCustomerInfo().getPhone();
        smsFrequency.put(phoneNumber, smsFrequency.getOrDefault(phoneNumber, 0) + 1);
    }

    private void analyzeSMS(Notification notification) {
        String phoneNumber = notification.getAccount().getCustomerInfo().getEmail();
        mailFrequency.put(phoneNumber, mailFrequency.getOrDefault(phoneNumber, 0) + 1);
    }
}
