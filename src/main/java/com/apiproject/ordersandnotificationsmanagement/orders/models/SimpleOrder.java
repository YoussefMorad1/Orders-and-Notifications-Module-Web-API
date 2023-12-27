package com.apiproject.ordersandnotificationsmanagement.orders.models;

import com.apiproject.ordersandnotificationsmanagement.accounts.models.Account;
import com.apiproject.ordersandnotificationsmanagement.products.models.Product;

import com.apiproject.ordersandnotificationsmanagement.products.models.ProductItem;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;


@Getter
@Setter
public class SimpleOrder extends Order {
    private String location;
    private double totalPrice;
    private String username;
    @JsonIgnore
    private Account account;
    private ArrayList<ProductItem> products;
    public SimpleOrder(String orderID, double shippingFee, boolean isShipping, Date setTime,
                       String location, double totalPrice, Account account, ArrayList<ProductItem> products) {
        super(orderID, shippingFee, isShipping, setTime);
        this.location = location;
        this.totalPrice = totalPrice;
        this.account = account;
        this.products = products;
        this.username = account.getAccountCredentials().getUsername();
    }
    @JsonIgnore
    @Override
    public ArrayList<SimpleOrder> getOrderAsList() {
        ArrayList<SimpleOrder> orders = new ArrayList<>();
        orders.add(this);
        return orders;
    }
}