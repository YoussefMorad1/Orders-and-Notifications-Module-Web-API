package com.apiproject.ordersandnotificationsmanagement.orders.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SimpleOrder extends Order{
    private String location;
    private double totalPrice;
    //array of products
    //order's account
    public SimpleOrder(String orderID, double shippingFee, boolean isShipping, String setTime) {
        super(orderID, shippingFee, isShipping, setTime);
    }



}
