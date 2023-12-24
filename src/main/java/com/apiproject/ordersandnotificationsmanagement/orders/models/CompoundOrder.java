package com.apiproject.ordersandnotificationsmanagement.orders.models;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;

@Getter
@Setter
public class CompoundOrder extends Order{
    private String commonLocation;
    private ArrayList<SimpleOrder> simpleOrders;
    public CompoundOrder(String orderID, double shippingFee, boolean isShipping, Date setTime,
                         String commonLocation, ArrayList<SimpleOrder> simpleOrders) {
        super(orderID, shippingFee, isShipping, setTime);
        this.commonLocation = commonLocation;
        this.simpleOrders = simpleOrders;
    }

    @Override
    public ArrayList<SimpleOrder> getOrderAsList() {
        return new ArrayList<>(simpleOrders);
    }
}
