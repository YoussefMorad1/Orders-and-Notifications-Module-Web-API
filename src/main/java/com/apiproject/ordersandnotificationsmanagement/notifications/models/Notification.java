package com.apiproject.ordersandnotificationsmanagement.notifications.models;

import com.apiproject.ordersandnotificationsmanagement.accounts.models.Account;
import com.apiproject.ordersandnotificationsmanagement.notifications.enums.Language;
import com.apiproject.ordersandnotificationsmanagement.notifications.enums.NotificationChannel;
import com.apiproject.ordersandnotificationsmanagement.notifications.services.messagetemplates.MessageTemplate;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.nio.channels.Channel;

@Getter
@Setter
public class Notification {
    private String subject;
    private String message;
    @JsonIgnore
    private Account account;
    private NotificationChannel channel;
    private Language language;
    @JsonIgnore
    private MessageTemplate notificationTemplate;
    public Notification(String subject, String message, Account account, Language language, MessageTemplate notificationTemplate) {
        this.subject = subject;
        this.message = message;
        this.account = account;
        this.language = language;
        this.notificationTemplate = notificationTemplate;
    }

    public Notification(Notification notification) {
        this.subject = notification.getSubject();
        this.message = notification.getMessage();
        this.account = notification.getAccount();
        this.channel = notification.getChannel();
        this.language = notification.getLanguage();
        this.notificationTemplate = notification.getNotificationTemplate();
    }

    @JsonInclude
    public String getUserName(){
        return account.getAccountCredentials().getUsername();
    }
}
