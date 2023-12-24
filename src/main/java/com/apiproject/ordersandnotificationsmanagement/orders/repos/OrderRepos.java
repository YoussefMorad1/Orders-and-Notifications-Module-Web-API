package com.apiproject.ordersandnotificationsmanagement.orders.repos;

import com.apiproject.ordersandnotificationsmanagement.accounts.models.Account;
import com.apiproject.ordersandnotificationsmanagement.orders.models.Order;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
@Repository
public class OrderRepos {
    private final ArrayList<Order>orders;

    public OrderRepos() {
        orders = new ArrayList<>();
    }


    public void addOrder(Order order) {

        orders.add(order);
    }
    public Order getOrder(String id) {
        for (Order order : orders) {
            if (order.getOrderID().equals(id)) {
                return order;
            }
        }
        return null;
    }
    public void cancleOrder(Order order){
        orders.remove(order);
    }

    //deduct


}
