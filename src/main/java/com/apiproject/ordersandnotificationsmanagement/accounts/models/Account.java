package com.apiproject.ordersandnotificationsmanagement.accounts.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Account {
    private AccountCredentials accountCredentials;
    private CustomerInfo customerInfo;
    private double balance;
}

/**
 {
     "balance": 200,
     "customerInfo": {
         "name": "youssef",
         "email": "youssef@mail.com",
         "phone": "1234"
     },
     "accountCredentials": {
         "username": "youssef1",
         "password": "admin"
     }
 }
 */