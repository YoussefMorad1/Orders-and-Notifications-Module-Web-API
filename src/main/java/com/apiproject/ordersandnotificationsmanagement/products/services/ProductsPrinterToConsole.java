package com.apiproject.ordersandnotificationsmanagement.products.services;

import com.apiproject.ordersandnotificationsmanagement.products.models.Product;
import com.apiproject.ordersandnotificationsmanagement.products.repos.ProductsRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@AllArgsConstructor
public class ProductsPrinterToConsole implements ApplicationRunner {

    private final ProductsRepo productsRepo;
    @Override
    public void run(ApplicationArguments args) {
        printInitialData();
    }
    private void printInitialData() {
        System.out.println("Hi TA ;) I'm printing the initial available products for you to use them in your api request :)");
        System.out.println("[Note you can also get all available products by sending a GET request to '/products/all' but now no need to make that effort]");
        System.out.println("Products:");
        ArrayList<Product> products = productsRepo.getProducts();
        ObjectMapper objectMapper = new ObjectMapper();
        for (Product product : products) {
            System.out.println(objectMapper.valueToTree(product));
        }
    }
}