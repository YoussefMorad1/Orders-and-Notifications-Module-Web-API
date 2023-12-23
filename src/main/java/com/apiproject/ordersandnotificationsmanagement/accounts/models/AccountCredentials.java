package com.apiproject.ordersandnotificationsmanagement.accounts.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class AccountCredentials {
    private String username;
    private String password;
}
