package com.apiproject.ordersandnotificationsmanagement.products.models;

import com.apiproject.ordersandnotificationsmanagement.products.enums.Category;
import com.apiproject.ordersandnotificationsmanagement.products.enums.Vendor;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
@AllArgsConstructor
@Getter
@Setter
public class ProductItem{
    private Product product;
    private String serialNumber;
}
