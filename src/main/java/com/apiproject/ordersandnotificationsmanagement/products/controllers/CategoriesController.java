package com.apiproject.ordersandnotificationsmanagement.products.controllers;

import com.apiproject.ordersandnotificationsmanagement.products.enums.Category;
import com.apiproject.ordersandnotificationsmanagement.products.services.CategoriesService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/products/categories")
public class CategoriesController {
    CategoriesService categoryService;
    @GetMapping("/get_products_count/{category}")
    public Integer getCategoryRemainingProductsCount(@PathVariable Category category){
        return categoryService.getCategoryRemainingProductsCount(category);
    }
}
