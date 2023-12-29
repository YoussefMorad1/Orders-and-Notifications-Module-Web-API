package com.apiproject.ordersandnotificationsmanagement.notifications.services.messagetemplates;

import com.apiproject.ordersandnotificationsmanagement.accounts.models.Account;
import com.apiproject.ordersandnotificationsmanagement.accounts.services.AccountsService;
import com.apiproject.ordersandnotificationsmanagement.notifications.enums.Language;
import com.apiproject.ordersandnotificationsmanagement.notifications.enums.NotificationChannel;
import com.apiproject.ordersandnotificationsmanagement.notifications.models.Notification;
import com.apiproject.ordersandnotificationsmanagement.orders.services.OrdersService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;

@Component
public class SetOrderMessageTemplate extends MessageTemplate {
    public SetOrderMessageTemplate(AccountsService accountsService, OrdersService ordersService) {
        super(accountsService, ordersService);
        String subject = "Order #%s has been set";
        String message = "Dear %s, your order #%s has been set.";
        ArrayList<NotificationChannel> availableChannels = new ArrayList<>();
        availableChannels.add(NotificationChannel.SMS);
        availableChannels.add(NotificationChannel.EMAIL);

        HashMap<Language, String> languageToMessageMap = new HashMap<>();
        languageToMessageMap.put(Language.ENGLISH, message);
        languageToMessageMap.put(Language.ARABIC, "عزيزي %s، تم تأكيد طلبك برقم %s.");

        HashMap<Language, String> languageToSubjectMap = new HashMap<>();
        languageToSubjectMap.put(Language.ENGLISH, subject);
        languageToSubjectMap.put(Language.ARABIC, "تم تأكيد الطلب برقم %s");

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
        String subject = String.format(this.getLanguageToSubjectMap().get(language), orderId);
        String message = String.format(this.getLanguageToMessageMap().get(language), account.getCustomerInfo().getName(), orderId);
        return new Notification(subject, message, account, channel, language, this);
    }

    @Override
    public String toString() {
        return "SetOrderMessageTemplate";
    }
}
