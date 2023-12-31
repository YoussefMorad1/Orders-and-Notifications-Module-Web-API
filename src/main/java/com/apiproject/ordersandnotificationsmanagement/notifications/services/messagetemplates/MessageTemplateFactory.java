package com.apiproject.ordersandnotificationsmanagement.notifications.services.messagetemplates;

import com.apiproject.ordersandnotificationsmanagement.accounts.services.AccountsService;
import com.apiproject.ordersandnotificationsmanagement.notifications.enums.NotificationReason;
import com.apiproject.ordersandnotificationsmanagement.notifications.services.messagetemplates.MessageTemplate;
import com.apiproject.ordersandnotificationsmanagement.notifications.services.messagetemplates.SetOrderMessageTemplate;
import com.apiproject.ordersandnotificationsmanagement.notifications.services.messagetemplates.ShipOrderMessageTemplate;
import com.apiproject.ordersandnotificationsmanagement.orders.services.OrdersService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class MessageTemplateFactory {
    HashMap<NotificationReason, MessageTemplate> notificationTypeToMessageTemplateMap;
    AccountsService accountsService;
    OrdersService ordersService;
    MessageTemplateFactory(AccountsService accountsService, OrdersService ordersService) {
        this.accountsService = accountsService;
        this.ordersService = ordersService;
        notificationTypeToMessageTemplateMap = new HashMap<>();
        // We save one object of each type of MessageTemplate
        // in the HashMap and return it whenever we need it
        // instead of creating a new object every time
        notificationTypeToMessageTemplateMap.put(NotificationReason.ORDER_CREATED,
                new SetOrderMessageTemplate(accountsService, ordersService));

        notificationTypeToMessageTemplateMap.put(NotificationReason.ORDER_SHIPPED,
                new ShipOrderMessageTemplate(accountsService, ordersService));
    }
    public MessageTemplate getMessageTemplate(NotificationReason notificationReason) {
        return switch (notificationReason) {
            case ORDER_CREATED -> notificationTypeToMessageTemplateMap.get(NotificationReason.ORDER_CREATED);
            case ORDER_SHIPPED -> notificationTypeToMessageTemplateMap.get(NotificationReason.ORDER_SHIPPED);
            default -> throw new IllegalArgumentException("Invalid notification type");
        };
    }
}
