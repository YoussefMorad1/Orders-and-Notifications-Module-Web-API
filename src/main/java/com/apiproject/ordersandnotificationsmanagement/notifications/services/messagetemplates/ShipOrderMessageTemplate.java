package com.apiproject.ordersandnotificationsmanagement.notifications.services.messagetemplates;

import com.apiproject.ordersandnotificationsmanagement.accounts.models.Account;
import com.apiproject.ordersandnotificationsmanagement.accounts.services.AccountsService;
import com.apiproject.ordersandnotificationsmanagement.notifications.enums.Language;
import com.apiproject.ordersandnotificationsmanagement.notifications.enums.NotificationChannel;
import com.apiproject.ordersandnotificationsmanagement.notifications.models.Notification;
import com.apiproject.ordersandnotificationsmanagement.orders.models.Order;
import com.apiproject.ordersandnotificationsmanagement.orders.models.SimpleOrder;
import com.apiproject.ordersandnotificationsmanagement.orders.services.OrdersService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;

@Component
public class ShipOrderMessageTemplate extends MessageTemplate{

    ShipOrderMessageTemplate(AccountsService accountsService, OrdersService ordersService) {
        super(accountsService, ordersService);
        String subject = "Order #%s started shipping";
        String message = "Dear %s, your order #%s is on the way. The destination is %s.";
        ArrayList<NotificationChannel> availableChannels = new ArrayList<>();
        availableChannels.add(NotificationChannel.EMAIL);

        HashMap<Language, String> languageToMessageMap = new HashMap<>();
        languageToMessageMap.put(Language.ENGLISH, message);
        languageToMessageMap.put(Language.ARABIC, "عزيزي %s، طلبك برقم %s في الطريق. الوجهة هي %s.");

        HashMap<Language, String> languageToSubjectMap = new HashMap<>();
        languageToSubjectMap.put(Language.ENGLISH, subject);
        languageToSubjectMap.put(Language.ARABIC, "بدأ الطلب برقم %s في الشحن");

        this.setSubject(subject);
        this.setMessage(message);
        this.setAvailableChannels(availableChannels);
        this.setLanguageToSubjectMap(languageToSubjectMap);
        this.setLanguageToMessageMap(languageToMessageMap);
    }
    @Override
    public Notification getNotification(String username, String orderId, Language language, NotificationChannel channel) {
        if (!this.isValidNotificationData(username, orderId, language, channel)) {
            return null;
        }
        Account account = accountsService.getAccount(username);
        Order order = ordersService.getOrder(orderId, true);
        String subject = String.format(this.getLanguageToSubjectMap().get(language), orderId);
        String message = String.format(this.getLanguageToMessageMap().get(language),
                                       account.getCustomerInfo().getName(),
                                       orderId, ((SimpleOrder) order).getLocation());
        return new Notification(subject, message, account, channel, language, this);
    }

    @Override
    public String toString() {
        return "ShipOrderMessageTemplate";
    }
}
