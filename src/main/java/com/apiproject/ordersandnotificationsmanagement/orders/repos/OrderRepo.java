package com.apiproject.ordersandnotificationsmanagement.orders.repos;

import com.apiproject.ordersandnotificationsmanagement.orders.models.Order;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class OrderRepo {
    private final ArrayList<Order> orders;
    public OrderRepo() {
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
    public void removeOrder(Order order) {
        orders.remove(order);
    }
    public void setOrderShippingStatus(String id, boolean status) {
        Order order = getOrder(id);
        if (order == null) {
            throw new IllegalArgumentException("Order not found");
        }
        order.setShipping(status);
    }
}

