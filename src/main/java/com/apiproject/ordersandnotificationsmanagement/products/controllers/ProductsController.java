package com.apiproject.ordersandnotificationsmanagement.products.controllers;

import com.apiproject.ordersandnotificationsmanagement.products.models.Product;
import com.apiproject.ordersandnotificationsmanagement.products.models.ProductItem;
import com.apiproject.ordersandnotificationsmanagement.products.services.ProductsService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductsController {
    private ProductsService productsService;

    @GetMapping("/all")
    public ResponseEntity<ArrayList<Product>> getAllProducts() {
        return ResponseEntity.ok(productsService.getAllProducts());
    }

    @GetMapping("/productItem/{productID}")
    public ResponseEntity<?> getProductItem(@PathVariable String productID) {
        Product product = productsService.getProduct(productID);
        if (product == null) {
            return ResponseEntity.badRequest().body("Product not found");
        }
        ProductItem productItem = productsService.getProductItem(product);
        if (productItem == null) {
            return ResponseEntity.badRequest().body("Product is out of stock");
        }
        return ResponseEntity.ok(productItem);
    }
}
