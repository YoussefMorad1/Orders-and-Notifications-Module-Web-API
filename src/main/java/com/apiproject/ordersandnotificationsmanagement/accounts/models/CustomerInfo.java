package com.apiproject.ordersandnotificationsmanagement.accounts.models;

import com.apiproject.ordersandnotificationsmanagement.notifications.enums.Language;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CustomerInfo {
    @NonNull
    private String name;
    @NonNull
    private String email;
    @NonNull
    private String phone;
    @NonNull
    private Language language;
}
