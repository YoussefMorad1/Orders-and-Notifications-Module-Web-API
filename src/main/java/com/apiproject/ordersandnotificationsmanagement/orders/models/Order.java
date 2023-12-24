package com.apiproject.ordersandnotificationsmanagement.orders.models;

import com.apiproject.ordersandnotificationsmanagement.accounts.models.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor

public abstract class Order {
    protected String orderID;
    protected double shippingFee;
    protected boolean isShipping;
    protected Date setTime;
    public abstract ArrayList<SimpleOrder> getOrderAsList();
}
