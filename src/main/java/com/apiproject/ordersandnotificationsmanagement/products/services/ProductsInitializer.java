package com.apiproject.ordersandnotificationsmanagement.products.services;

import com.apiproject.ordersandnotificationsmanagement.products.enums.Category;
import com.apiproject.ordersandnotificationsmanagement.products.enums.Vendor;
import com.apiproject.ordersandnotificationsmanagement.products.models.Product;
import com.apiproject.ordersandnotificationsmanagement.products.models.ProductItem;
import com.apiproject.ordersandnotificationsmanagement.products.repos.CategoriesRepo;
import com.apiproject.ordersandnotificationsmanagement.products.repos.ProductsRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Component
public class ProductsInitializer {
    private ProductsRepo productsRepo;
    private CategoriesRepo categoriesRepo;
    public ProductsInitializer(ProductsRepo productsRepo, CategoriesRepo categoriesRepo){
        this.productsRepo = productsRepo;
        this.categoriesRepo = categoriesRepo;
        putInitialData();
    }

    private void putInitialData(){
        Product product1 = new Product("1", "Clean Code Book", 100, Category.BOOKS, Vendor.AMAZON, new ArrayList<>());
        product1.getProductItems().add(new ProductItem(product1, "1"));
        product1.getProductItems().add(new ProductItem(product1, "2"));
        product1.getProductItems().add(new ProductItem(product1, "3"));
        product1.getProductItems().add(new ProductItem(product1, "4"));
        productsRepo.addProduct(product1);
        categoriesRepo.addProduct(product1);

        Product product2 = new Product("2", "Sun Block cream", 200, Category.BEAUTY, Vendor.ALIBABA, new ArrayList<>());
        product2.getProductItems().add(new ProductItem(product2, "1"));
        product2.getProductItems().add(new ProductItem(product2, "2"));
        product2.getProductItems().add(new ProductItem(product2, "3"));
        productsRepo.addProduct(product2);
        categoriesRepo.addProduct(product2);

        Product product3 = new Product("3", "Clean Architecture Book", 300, Category.BOOKS, Vendor.AMAZON, new ArrayList<>());
        product3.getProductItems().add(new ProductItem(product3, "1"));
        product3.getProductItems().add(new ProductItem(product3, "2"));
        product3.getProductItems().add(new ProductItem(product3, "3"));
        productsRepo.addProduct(product3);
        categoriesRepo.addProduct(product3);
    }
}
