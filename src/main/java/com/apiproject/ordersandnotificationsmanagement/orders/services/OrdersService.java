package com.apiproject.ordersandnotificationsmanagement.orders.services;

import com.apiproject.ordersandnotificationsmanagement.orders.models.Order;

import com.apiproject.ordersandnotificationsmanagement.orders.repos.OrderRepos;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;


@AllArgsConstructor
@Service
public class OrdersService {
    private  final OrderRepos orderRepos;
    public void placeOrder(Order order){
        orderRepos.addOrder(order);
    }
    public boolean shipOrder(Order order) {
        if (order == null || !orderRepos.isPlacingOrder(order.getOrderID())) {
            return false;
        }
        return true;
    }
    public Order getOrder(String orderID){
        return orderRepos.getOrder(orderID);
    }


}
