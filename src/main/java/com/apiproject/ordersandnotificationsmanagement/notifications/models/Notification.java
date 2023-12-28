package com.apiproject.ordersandnotificationsmanagement.notifications.models;

import com.apiproject.ordersandnotificationsmanagement.accounts.models.Account;
import com.apiproject.ordersandnotificationsmanagement.notifications.enums.Language;
import com.apiproject.ordersandnotificationsmanagement.notifications.enums.NotificationChannel;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private String username;
    private NotificationChannel channel;
    private Language language;
    public Notification(String subject, String message, Account account, NotificationChannel channel, Language language) {
        this.subject = subject;
        this.message = message;
        this.account = account;
        this.username = account.getAccountCredentials().getUsername();
        this.channel = channel;
        this.language = language;
    }
}
