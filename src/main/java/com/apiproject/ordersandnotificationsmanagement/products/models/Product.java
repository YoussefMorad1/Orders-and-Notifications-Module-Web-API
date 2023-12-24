package com.apiproject.ordersandnotificationsmanagement.products.models;

import com.apiproject.ordersandnotificationsmanagement.products.enums.Category;
import com.apiproject.ordersandnotificationsmanagement.products.enums.Vendor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
@Getter
@Setter
@AllArgsConstructor
public class Product {
    protected String id;
    protected String name;
    protected double price;
    protected Category category;
    protected Vendor vendor;
    private ArrayList<ProductItem> productItems;
    public ProductItem getOneProductItem(){
        if(productItems.isEmpty()){
            return null;
        }
        return productItems.get(0);
    }
}
