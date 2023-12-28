package com.apiproject.ordersandnotificationsmanagement.accounts.models;

import com.apiproject.ordersandnotificationsmanagement.notifications.enums.Language;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CustomerInfo {
    private String name;
    private String email;
    private String phone;
    private Language language;
}
