package com.apiproject.ordersandnotificationsmanagement.orders.controllers;

import com.apiproject.ordersandnotificationsmanagement.orders.models.Order;
import com.apiproject.ordersandnotificationsmanagement.orders.services.OrdersService;
import com.apiproject.ordersandnotificationsmanagement.products.models.Product;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/orders")
@AllArgsConstructor
public class OrdersController {
    private OrdersService ordersService;

    @PostMapping("/place")
    public ResponseEntity<String> placeOrder(@RequestBody Order order) {
        ordersService.placeOrder(order);
        return ResponseEntity.status(HttpStatus.CREATED).body("Order is placed successfully");
    }

    @PostMapping("/ship")
    public ResponseEntity<String> shipOrder(@RequestBody Order order) {
        if (ordersService.shipOrder(order)) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Order is shipped successfully");
        } else {
            return ResponseEntity.badRequest().body("Place order first");
        }
    }

    @GetMapping("/order/{orderID}")
    public ResponseEntity<?> getOrderInfo(@PathVariable String orderID) {
        Order order = ordersService.getOrder(orderID);
        if (order == null) {
            return ResponseEntity.badRequest().body("order not found");
        } else {
            return ResponseEntity.ok(order);
        }


    }
}
