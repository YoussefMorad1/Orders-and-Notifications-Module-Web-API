package com.apiproject.ordersandnotificationsmanagement.orders.services;

import com.apiproject.ordersandnotificationsmanagement.orders.repos.OrderRepos;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;


@AllArgsConstructor
@Service
public class OrdersService {
    private  final OrderRepos orderRepos;
}
