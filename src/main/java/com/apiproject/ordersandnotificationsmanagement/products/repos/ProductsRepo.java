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
        Product product1 = new Product("1", "product1", 100, Category.BOOKS, Vendor.AMAZON, new ArrayList<>());
        product1.getProductItems().add(new ProductItem(product1, "1"));
        product1.getProductItems().add(new ProductItem(product1, "2"));
        product1.getProductItems().add(new ProductItem(product1, "3"));
        products.add(product1);
        Product product2 = new Product("2", "product2", 200, Category.BEAUTY, Vendor.ALIBABA, new ArrayList<>());
        product2.getProductItems().add(new ProductItem(product2, "1"));
        product2.getProductItems().add(new ProductItem(product2, "2"));
        product2.getProductItems().add(new ProductItem(product2, "3"));
        products.add(product2);
    }
    public Product getProductById(String id){
        for(Product product: products){
            if(product.getId().equals(id)){
                return product;
            }
        }
        return null;
    }
}
