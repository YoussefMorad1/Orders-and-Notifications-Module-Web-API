package com.apiproject.ordersandnotificationsmanagement.orders.repos;

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
    public void cancelOrder(Order order){

        orders.remove(order);
    }
    public boolean isPlacingOrder(String id){
        for (Order order : orders) {
            if (order.getOrderID().equals(id)) {
                return true;
            }
        }
        return false;
    }

   /* public void deductBalance(Order order, Account account){
        account.setBalance(order);
    }*/


}

