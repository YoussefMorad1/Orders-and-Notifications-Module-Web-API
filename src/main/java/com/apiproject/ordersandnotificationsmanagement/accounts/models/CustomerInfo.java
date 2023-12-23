package com.apiproject.ordersandnotificationsmanagement.accounts.models;

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
}
