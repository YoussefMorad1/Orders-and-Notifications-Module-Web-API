package com.apiproject.ordersandnotificationsmanagement.orders.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor

public abstract class Order {
    protected String orderID;
    protected double shippingFee;
    protected boolean isShipping;
    protected String setTime;
  /*  public ArrayList<Order> getOrderAsList(){

    }*/

}
