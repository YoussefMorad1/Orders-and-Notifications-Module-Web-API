package com.apiproject.ordersandnotificationsmanagement.orders.services;

import com.apiproject.ordersandnotificationsmanagement.accounts.models.Account;
import com.apiproject.ordersandnotificationsmanagement.accounts.repos.AccountsRepo;
import com.apiproject.ordersandnotificationsmanagement.notifications.enums.NotificationReason;
import com.apiproject.ordersandnotificationsmanagement.notifications.services.NotificationsService;
import com.apiproject.ordersandnotificationsmanagement.orders.models.CompoundOrder;
import com.apiproject.ordersandnotificationsmanagement.orders.models.Order;

import com.apiproject.ordersandnotificationsmanagement.orders.models.SimpleOrder;
import com.apiproject.ordersandnotificationsmanagement.orders.models.inputs.CompoundOrderInput;
import com.apiproject.ordersandnotificationsmanagement.orders.models.inputs.OrderInput;
import com.apiproject.ordersandnotificationsmanagement.orders.models.inputs.SimpleOrderInput;
import com.apiproject.ordersandnotificationsmanagement.orders.repos.OrderRepo;
import com.apiproject.ordersandnotificationsmanagement.products.models.Product;
import com.apiproject.ordersandnotificationsmanagement.products.models.ProductItem;
import com.apiproject.ordersandnotificationsmanagement.products.services.ProductsService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

import static com.apiproject.ordersandnotificationsmanagement.notifications.enums.NotificationReason.ORDER_CREATED;

@Service
public class OrdersService {
    private final OrderRepo orderRepo;
    private final ProductsService productsService;
    private final AccountsRepo accountsService;
    private final NotificationsService notificationsService;
    private final long maxDurationToCancelShipping = 1000 * 60 * 2L; // 1000 milliseconds * (60 seconds) * (2 minute)
    private final double shippingFeeFactor = 0.1;
    private static int curOrderID = 0;

    OrdersService(OrderRepo orderRepo, ProductsService productsService, @Lazy NotificationsService notificationsService, AccountsRepo accountsService) {
        this.orderRepo = orderRepo;
        this.productsService = productsService;
        this.notificationsService = notificationsService;
        this.accountsService = accountsService;
    }

    public boolean placeOrder(Order order) {
        if (orderRepo.getOrder(order.getOrderID(), true) != null
                || !placingOrderDeductBalance(order)) {
            return false;
        }
        removeOrderProductItems(order);
        orderRepo.addOrder(order);

        ArrayList<SimpleOrder> orders = order.getOrderAsList();
        for (SimpleOrder o : orders)
            notificationsService.sendNotification(o, ORDER_CREATED);
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

    private boolean placingOrderDeductBalance(Order order) {
        ArrayList<SimpleOrder> orders = order.getOrderAsList();
        for (SimpleOrder o : orders) {
            Account account = o.getAccount();
            if (account.getBalance() < o.getItemsTotalPrice()) {
                return false;
            }
            account.setBalance(account.getBalance() - o.getItemsTotalPrice());
        }
        return true;
    }

    public boolean shipOrder(String orderId) {
        Order order = orderRepo.getOrder(orderId, false);
        if (order == null || order.isShipping() || !shippingOrderDeductBalance(order)) {
            return false;
        }
        orderRepo.setOrderShippingStatus(orderId, true);

        ArrayList<SimpleOrder> orders = order.getOrderAsList();
        for (SimpleOrder o : orders)
            notificationsService.sendNotification(o, NotificationReason.ORDER_SHIPPED);
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

    public Order getOrder(String orderID, boolean searchInsideCompoundOrders) {
        return orderRepo.getOrder(orderID, searchInsideCompoundOrders);
    }

    public boolean cancelOrder(String orderID) {
        Order order = orderRepo.getOrder(orderID, false);
        if (order == null || order.isShipping()) {
            return false;
        }
        orderRepo.removeOrder(order);

        ArrayList<SimpleOrder> orders = order.getOrderAsList();
        for (SimpleOrder o : orders) {
            Account account = o.getAccount();
            account.setBalance(account.getBalance() + o.getItemsTotalPrice());
        }

        return true;
    }

    public boolean cancelShipping(String orderID) {
        Order order = orderRepo.getOrder(orderID, false);
        Date now = new Date();
        if (order == null)
            return false;
        long diff = now.getTime() - order.getSetTime().getTime();
        if (!order.isShipping() || diff > maxDurationToCancelShipping) {
            return false;
        }
        orderRepo.setOrderShippingStatus(orderID, false);
        // NO NEED TO REFUND THE SHIPPING FEE, BECAUSE THE SHIPPING ALREADY HAPPENED
        return true;
    }

    public Order getOrderFromOrderInput(OrderInput orderInput) {
        Order order;
        ArrayList<SimpleOrderInput> simpleOrders = new ArrayList<>();

        if (orderInput instanceof SimpleOrderInput) {
            simpleOrders.add((SimpleOrderInput) orderInput);
        } else {
            simpleOrders = ((CompoundOrderInput) orderInput).getOrders();
        }

        ArrayList<SimpleOrder> simpleOrdersList = new ArrayList<>();
        double maxShippingFeeOfSimpleOrders = 0;
        for (SimpleOrderInput simpleOrderInput : simpleOrders) {
            // Getting List of ProductItems from Product IDs
            ArrayList<String> productsIDs = simpleOrderInput.getProductsIDs();
            ArrayList<ProductItem> products = getProductItemsFromProductIDs(productsIDs);
            if (products == null) {
                return null;
            }
            double totalPrice = calculateTotalPrice(products);

            // Getting Account from Account ID
            String username = simpleOrderInput.getUserName();
            Account account = accountsService.getAccount(username);
            if (account == null) {
                return null;
            }

            curOrderID++;
            maxShippingFeeOfSimpleOrders = Math.max(maxShippingFeeOfSimpleOrders, totalPrice * shippingFeeFactor);
            simpleOrdersList.add(new SimpleOrder(String.valueOf(curOrderID), totalPrice * shippingFeeFactor,
                    false, new Date(), simpleOrderInput.getLocation(), totalPrice, account, products));
        }

        if (orderInput instanceof SimpleOrderInput) {
            order = simpleOrdersList.get(0);
        } else {
            curOrderID++;
            String commonLocation = ((CompoundOrderInput) orderInput).getCommonLocation();
            order = new CompoundOrder(String.valueOf(curOrderID), maxShippingFeeOfSimpleOrders,
                    false, new Date(), commonLocation, simpleOrdersList);
        }
        return order;
    }

    private double calculateTotalPrice(ArrayList<ProductItem> products) {
        double totalPrice = 0;
        for (ProductItem p : products) {
            totalPrice += p.getProduct().getPrice();
        }
        return totalPrice;
    }

    private ArrayList<ProductItem> getProductItemsFromProductIDs(ArrayList<String> productsIDs) {
        ArrayList<ProductItem> productItems = new ArrayList<>();
        for (String productID : productsIDs) {
            Product curProduct = productsService.getProduct(productID);
            if (curProduct == null) {
                return null;
            }
            ProductItem productItem = curProduct.getOneProductItem();
            if (productItem == null) {
                return null;
            }
            productItems.add(productItem);
        }
        return productItems;
    }
}
