package com.apiproject.ordersandnotificationsmanagement.orders.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public abstract class Order {
    protected String orderID;
    protected double shippingFee;
    protected boolean isShipping;
    protected String setTime;

}
