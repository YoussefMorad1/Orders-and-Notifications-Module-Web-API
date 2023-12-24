package com.apiproject.ordersandnotificationsmanagement.products.models;

import com.apiproject.ordersandnotificationsmanagement.products.enums.Category;
import com.apiproject.ordersandnotificationsmanagement.products.enums.Vendor;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
@AllArgsConstructor
public class ProductItem{
    private Product product;
    private String serialNumber;

}
