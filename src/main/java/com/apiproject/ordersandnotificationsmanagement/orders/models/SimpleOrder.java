package com.apiproject.ordersandnotificationsmanagement.orders.models;

import com.apiproject.ordersandnotificationsmanagement.accounts.models.Account;
import com.apiproject.ordersandnotificationsmanagement.products.models.Product;

import com.apiproject.ordersandnotificationsmanagement.products.models.ProductItem;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;


@Getter
@Setter
public class SimpleOrder extends Order {
    private double ItemsTotalPrice;
    private String location;
    @JsonIgnore
    private Account account;
    private ArrayList<ProductItem> products;
    public SimpleOrder(String orderID, double shippingFee, boolean isShipping, Date setTime,
                       @NonNull String location, double ItemsTotalPrice, @NonNull Account account, @NonNull ArrayList<ProductItem> products) {
        super(orderID, shippingFee, isShipping, setTime);
        this.location = location;
        this.ItemsTotalPrice = ItemsTotalPrice;
        this.account = account;
        this.products = products;
    }
    @JsonIgnore
    @Override
    public ArrayList<SimpleOrder> getOrderAsList() {
        ArrayList<SimpleOrder> orders = new ArrayList<>();
        orders.add(this);
        return orders;
    }
    @JsonInclude
    public String getUsername(){
        return account.getAccountCredentials().getUsername();
    }
}