package com.apiproject.ordersandnotificationsmanagement.orders.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;

@Getter
@Setter
public class CompoundOrder extends Order{
    private String commonLocation;
    private ArrayList<SimpleOrder> simpleOrders;
    public CompoundOrder(String orderID, double shippingFee, boolean isShipping, Date setTime,
                         @NonNull String commonLocation, @NonNull ArrayList<SimpleOrder> simpleOrders) {
        super(orderID, shippingFee, isShipping, setTime);
        this.commonLocation = commonLocation;
        this.simpleOrders = simpleOrders;
    }
    @JsonIgnore
    @Override
    public ArrayList<SimpleOrder> getOrderAsList() {
        return new ArrayList<>(simpleOrders);
    }
}
