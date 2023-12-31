package com.apiproject.ordersandnotificationsmanagement.notifications.services.messagetemplates;

import com.apiproject.ordersandnotificationsmanagement.accounts.models.Account;
import com.apiproject.ordersandnotificationsmanagement.accounts.services.AccountsService;
import com.apiproject.ordersandnotificationsmanagement.notifications.enums.NotificationChannel;
import com.apiproject.ordersandnotificationsmanagement.notifications.enums.Language;
import com.apiproject.ordersandnotificationsmanagement.notifications.models.Notification;
import com.apiproject.ordersandnotificationsmanagement.orders.models.CompoundOrder;
import com.apiproject.ordersandnotificationsmanagement.orders.models.Order;
import com.apiproject.ordersandnotificationsmanagement.orders.services.OrdersService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;

@AllArgsConstructor
@Getter
@Setter
public abstract class MessageTemplate {
    protected String subject;
    protected String message;
    protected ArrayList<NotificationChannel> availableChannels;
    protected HashMap<Language, String> languageToSubjectMap;
    protected HashMap<Language, String> languageToMessageMap;
    protected final AccountsService accountsService;
    protected final OrdersService ordersService;

    MessageTemplate(AccountsService accountsService, OrdersService ordersService) {
        this.accountsService = accountsService;
        this.ordersService = ordersService;
        this.subject = "";
        this.message = "";
        this.availableChannels = new ArrayList<>();
        this.languageToSubjectMap = new HashMap<>();
        this.languageToMessageMap = new HashMap<>();
    }

    public abstract Notification getNotification(String username, String orderId, Language language);
    protected boolean isValidNotificationData(String username, String orderId) {
        Account account = accountsService.getAccount(username);
        Order order = ordersService.getOrder(orderId, true);
        if (account == null || order == null || order instanceof CompoundOrder) {
            return false;
        }
        return true;
    }
    @Override
    public abstract String toString();
}
