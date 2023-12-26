package com.apiproject.ordersandnotificationsmanagement.products.repos;

import com.apiproject.ordersandnotificationsmanagement.products.enums.Category;
import com.apiproject.ordersandnotificationsmanagement.products.enums.Vendor;
import com.apiproject.ordersandnotificationsmanagement.products.models.Product;
import com.apiproject.ordersandnotificationsmanagement.products.models.ProductItem;
import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
@Repository
@Getter
public class ProductsRepo {
    private ArrayList<Product> products;
    public ProductsRepo(){
        products = new ArrayList<>();
    }
    public Product getProductById(String id){
        for(Product product: products){
            if(product.getId().equals(id)){
                return product;
            }
        }
        return null;
    }
    public void addProduct(Product product){
        products.add(product);
    }
}
