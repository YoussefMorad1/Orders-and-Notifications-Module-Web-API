package com.apiproject.ordersandnotificationsmanagement.orders.controllers;

import com.apiproject.ordersandnotificationsmanagement.orders.models.Order;
import com.apiproject.ordersandnotificationsmanagement.orders.models.inputs.OrderInput;
import com.apiproject.ordersandnotificationsmanagement.orders.models.inputs.SimpleOrderInput;
import com.apiproject.ordersandnotificationsmanagement.orders.services.OrdersService;
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
    public ResponseEntity<String> placeOrder(@RequestBody OrderInput orderInput) {
        Order order = ordersService.getOrderFromOrderInput(orderInput);
        if (ordersService.placeOrder(order)) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Order is placed successfully");
        } else {
            return ResponseEntity.badRequest().body("Order with this ID already exists");
        }
    }
    @PostMapping("/ship/{orderID}")
    public ResponseEntity<String> shipOrder(@PathVariable String orderID) {
        Order order = ordersService.getOrder(orderID, false);
        if (order == null) {
            return ResponseEntity.badRequest().body("order not found or is part of a compound order");
        } else if (ordersService.shipOrder(orderID)) {
            return ResponseEntity.ok("Order is shipped successfully");
        } else {
            return ResponseEntity.badRequest().body("Order is already shipped");
        }
    }
    @GetMapping("/get/{orderID}")
    public ResponseEntity<?> getOrderInfo(@PathVariable String orderID) {
        Order order = ordersService.getOrder(orderID, true);
        if (order == null) {
            return ResponseEntity.badRequest().body("order not found");
        } else {
            return ResponseEntity.ok(order);
        }
    }
    @DeleteMapping("/cancel/{orderID}")
    public ResponseEntity<String> cancelOrder(@PathVariable String orderID) {
        Order order = ordersService.getOrder(orderID, false);
        if (order == null) {
            return ResponseEntity.badRequest().body("order not found or is part of a compound order");
        } else if (ordersService.cancelOrder(orderID)) {
            return ResponseEntity.ok("Order is canceled successfully (balance is refunded)");
        } else {
            return ResponseEntity.badRequest().body("Order is already shipped");
        }
    }
    @PutMapping("/cancelShipping/{orderID}")
    public ResponseEntity<String> cancelShipping(@PathVariable String orderID) {
        Order order = ordersService.getOrder(orderID, false);
        if (order == null) {
            return ResponseEntity.badRequest().body("order not found or is part of a compound order");
        } else if (!order.isShipping()) {
            return ResponseEntity.badRequest().body("Order is not shipped yet");
        } else if (ordersService.cancelShipping(orderID)) {
            return ResponseEntity.ok("Order shipping is canceled successfully");
        } else {
            return ResponseEntity.badRequest().body("Order shipping time is expired (only 24 hours allowed)");
        }
    }
}
