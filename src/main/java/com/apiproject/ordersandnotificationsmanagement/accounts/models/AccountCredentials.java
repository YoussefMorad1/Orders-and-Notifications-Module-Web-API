package com.apiproject.ordersandnotificationsmanagement.accounts.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AccountCredentials {
    @NonNull
    private String username;
    @NonNull
    private String password;
}
