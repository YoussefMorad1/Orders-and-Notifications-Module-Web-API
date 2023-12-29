package com.apiproject.ordersandnotificationsmanagement.notifications.controllers;

import com.apiproject.ordersandnotificationsmanagement.notifications.services.NotificationsStatistics;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notifications/statistics")
@AllArgsConstructor
public class NotificationStatisticsController {
    NotificationsStatistics notificationsStatistics;
    @GetMapping("/get_most_notified_mail")
    public ResponseEntity<String> getMostNotifiedMail() {
        String mostNotifiedMail = notificationsStatistics.getMostNotifiedMail();
        return mostNotifiedMail == null ?
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("No notified mails found") :
                ResponseEntity.ok(mostNotifiedMail);
    }
    @GetMapping("/get_most_notified_phone")
    public ResponseEntity<String> getMostNotifiedPhone() {
        String mostNotifiedPhone = notificationsStatistics.getMostNotifiedPhone();
        return mostNotifiedPhone == null ?
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("No notified phone-number found") :
                ResponseEntity.ok(mostNotifiedPhone);
    }
    @GetMapping("/get_most_notified_template")
    public ResponseEntity<String> getMostNotifiedTemplate() {
        String mostNotifiedTemplate = notificationsStatistics.getMostNotifiedTemplate();
        return mostNotifiedTemplate == null ?
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("No notifications were sent") :
                ResponseEntity.ok(mostNotifiedTemplate);
    }
    @GetMapping("/get_most_notified_mail_or_phone")
    public ResponseEntity<String> getMostNotifiedMailOrPhone() {
        String mostNotifiedMailOrPhone = notificationsStatistics.getMostNotifiedMailOrPhone();
        return mostNotifiedMailOrPhone == null ?
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("No notifications were sent") :
                ResponseEntity.ok(mostNotifiedMailOrPhone);
    }
}
