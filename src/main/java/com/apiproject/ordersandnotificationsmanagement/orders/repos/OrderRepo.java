package com.apiproject.ordersandnotificationsmanagement.orders.repos;

import com.apiproject.ordersandnotificationsmanagement.orders.models.Order;
import com.apiproject.ordersandnotificationsmanagement.orders.models.SimpleOrder;
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
    public Order getOrder(String id, boolean searchInsideCompoundOrders) {
        for (Order order : orders) {
            if (order.getOrderID().equals(id)) {
                return order;
            }
            if (!searchInsideCompoundOrders) continue;
            // Check for simple orders inside compound orders
            ArrayList<SimpleOrder> insideSimpleOrders = order.getOrderAsList();
            for (SimpleOrder simpleOrder : insideSimpleOrders) {
                if (simpleOrder.getOrderID().equals(id)) {
                    return simpleOrder;
                }
            }
        }
        return null;
    }
    public void removeOrder(Order order) {
        orders.remove(order);
    }
    public void setOrderShippingStatus(String id, boolean status) {
        Order order = getOrder(id, false);
        if (order == null) {
            throw new IllegalArgumentException("Order not found");
        }
        order.setShipping(status);
        ArrayList<SimpleOrder> orders = order.getOrderAsList();
        for (SimpleOrder o : orders) {
            o.setShipping(status);
        }
    }
}

