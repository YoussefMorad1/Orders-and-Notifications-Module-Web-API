package com.apiproject.ordersandnotificationsmanagement.orders.models;

import com.apiproject.ordersandnotificationsmanagement.accounts.models.Account;
import com.apiproject.ordersandnotificationsmanagement.products.models.Product;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class SimpleOrder extends Order{
    private String location;
    private double totalPrice;
   private ArrayList<Product> products;
    private Account account;

    public SimpleOrder(String orderID, double shippingFee, boolean isShipping, String setTime) {
        super(orderID, shippingFee, isShipping, setTime);
    }



}
