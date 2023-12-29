package com.apiproject.ordersandnotificationsmanagement.notifications.services;

import com.apiproject.ordersandnotificationsmanagement.notifications.enums.NotificationChannel;
import com.apiproject.ordersandnotificationsmanagement.notifications.models.Notification;
import com.apiproject.ordersandnotificationsmanagement.notifications.repos.NotificationsStatisticsRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@AllArgsConstructor
public class NotificationsStatistics {
    NotificationsStatisticsRepo notificationsStatisticsRepo;
    public void analyze(Notification notification) {
        notificationsStatisticsRepo.addNotification(notification);
    }

    public String getMostNotifiedMail() {
        Map.Entry<String, Integer> maxEntry = getMaxEntryOfMap(notificationsStatisticsRepo.getMailFrequency());
        return maxEntry == null ? null : maxEntry.getKey();
    }

    public String getMostNotifiedPhone() {
        Map.Entry<String, Integer> maxEntry = getMaxEntryOfMap(notificationsStatisticsRepo.getSmsFrequency());
        return maxEntry == null ? null : maxEntry.getKey();
    }

    public String getMostNotifiedTemplate() {
        Map.Entry<String, Integer> maxEntry = getMaxEntryOfMap(notificationsStatisticsRepo.getMostUsedMessageTemplate());
        return maxEntry == null ? null : maxEntry.getKey();
    }

    // Returns the most notified mail or phone, if most notified mail and phone are different, returns the most notified one
    // If both have the same no of notifications, returns the mail
    public String getMostNotifiedMailOrPhone() {
        Map.Entry<String, Integer> maxEmailEntry = getMaxEntryOfMap(notificationsStatisticsRepo.getMailFrequency());
        Map.Entry<String, Integer> maxPhoneEntry = getMaxEntryOfMap(notificationsStatisticsRepo.getSmsFrequency());
        if (maxEmailEntry == null && maxPhoneEntry == null) {
            return null;
        } else if (maxEmailEntry == null) {
            return maxPhoneEntry.getKey();
        } else if (maxPhoneEntry == null) {
            return maxEmailEntry.getKey();
        } else {
            return maxEmailEntry.getValue() >= maxPhoneEntry.getValue() ?
                    maxEmailEntry.getKey() :
                    maxPhoneEntry.getKey();
        }
    }

    private Map.Entry<String, Integer> getMaxEntryOfMap(HashMap<String, Integer> data) {
        Map.Entry<String, Integer> maxEntry = null;
        for (Map.Entry<String, Integer> entry : data.entrySet()) {
            if (maxEntry == null || entry.getValue() > maxEntry.getValue()) {
                maxEntry = entry;
            }
        }
        return maxEntry;
    }
}
