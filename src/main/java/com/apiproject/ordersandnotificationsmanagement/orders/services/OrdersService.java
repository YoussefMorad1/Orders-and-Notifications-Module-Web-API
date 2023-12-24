package com.apiproject.ordersandnotificationsmanagement.orders.services;

import com.apiproject.ordersandnotificationsmanagement.accounts.models.Account;
import com.apiproject.ordersandnotificationsmanagement.orders.models.Order;

import com.apiproject.ordersandnotificationsmanagement.orders.models.SimpleOrder;
import com.apiproject.ordersandnotificationsmanagement.orders.repos.OrderRepo;
import com.apiproject.ordersandnotificationsmanagement.products.models.ProductItem;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;


@AllArgsConstructor
@Service
public class OrdersService {
    private final OrderRepo orderRepo;
    private final Long maxDurationToCancelShipping = 1000 * 60 * 60 * 24L;

    public boolean placeOrder(Order order) {
        if (orderRepo.getOrder(order.getOrderID()) != null || !placingOrderDeductBalance(order) || !isOrderValid(order)) {
            return false;
        }
        removeOrderProductItems(order);
        orderRepo.addOrder(order);
        return true;
    }

    private void removeOrderProductItems(Order order) {
        ArrayList<SimpleOrder> orders = order.getOrderAsList();
        for (SimpleOrder o : orders) {
            ArrayList<ProductItem> products = o.getProducts();
            for (ProductItem p : products) {
                p.getProduct().getProductItems().remove(p);
            }
        }
    }

    private boolean isOrderValid(Order order) {
        ArrayList<SimpleOrder> orders = order.getOrderAsList();
        for (SimpleOrder o : orders) { // for each simple order
            ArrayList<ProductItem> products = o.getProducts(); // get the products inside current simple order
            for (ProductItem p : products) {
                // make sure each product has at least one product item
                if (p.getProduct().getProductItems().isEmpty()) {
                    return false;
                }
                // make sure the product item is in the product (valid input from user/json)
                ArrayList<ProductItem> productItems = p.getProduct().getProductItems();
                boolean found = false;
                for (ProductItem pi : productItems) {
                    if (pi.getSerialNumber().equals(p.getSerialNumber())) {
                        // if product item is found inside parent product, then it's valid so far
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean placingOrderDeductBalance(Order order) {
        ArrayList<SimpleOrder> orders = order.getOrderAsList();
        for (SimpleOrder o : orders) {
            Account account = o.getAccount();
            if (account.getBalance() < o.getTotalPrice()) {
                return false;
            }
            account.setBalance(account.getBalance() - o.getTotalPrice());
        }
        return true;
    }

    public boolean shipOrder(String orderId) {
        Order order = orderRepo.getOrder(orderId);
        if (order == null || order.isShipping() || !shippingOrderDeductBalance(order)) {
            return false;
        }
        orderRepo.setOrderShippingStatus(orderId, true);
        return true;
    }

    private boolean shippingOrderDeductBalance(Order order) {
        double totalShippingFee = order.getShippingFee();
        ArrayList<SimpleOrder> orders = order.getOrderAsList();
        // Get all accounts that are involved in this order
        HashSet<Account> accounts = new HashSet<>();
        for (SimpleOrder o : orders) {
            Account account = o.getAccount();
            if (accounts.contains(account)) {
                continue;
            }
            accounts.add(account);
        }
        // calculate shipping fee per account
        double shippingFeePerAccount = totalShippingFee / accounts.size();
        // make sure all accounts have enough balance
        for (Account account : accounts) {
            if (account.getBalance() < shippingFeePerAccount) {
                return false;
            }
        }
        // deduct balance for the shipping fee
        for (Account account : accounts) {
            account.setBalance(account.getBalance() - shippingFeePerAccount);
        }
        return true;
    }

    public Order getOrder(String orderID) {
        return orderRepo.getOrder(orderID);
    }

    public boolean cancelOrder(String orderID) {
        Order order = orderRepo.getOrder(orderID);
        if (order == null || order.isShipping()) {
            return false;
        }
        orderRepo.removeOrder(order);
        return true;
    }

    public boolean cancelShipping(String orderID) {
        Order order = orderRepo.getOrder(orderID);
        Date now = new Date();
        long diff = now.getTime() - order.getSetTime().getTime();
        if (order == null || !order.isShipping() || diff > maxDurationToCancelShipping) {
            return false;
        }
        orderRepo.setOrderShippingStatus(orderID, false);
        return true;
    }
}
