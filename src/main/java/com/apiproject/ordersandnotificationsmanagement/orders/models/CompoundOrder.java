package com.apiproject.ordersandnotificationsmanagement.orders.models;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
@Getter
@Setter
public class CompoundOrder extends Order{
    private ArrayList<SimpleOrder> simpleOrders;
    private String commonLocation;
    public CompoundOrder(String orderID, double shippingFee, boolean isShipping, String setTime) {
        super(orderID, shippingFee, isShipping, setTime);
    }
}
