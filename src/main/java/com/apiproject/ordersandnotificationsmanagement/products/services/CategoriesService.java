package com.apiproject.ordersandnotificationsmanagement.products.services;

import com.apiproject.ordersandnotificationsmanagement.products.enums.Category;
import com.apiproject.ordersandnotificationsmanagement.products.models.Product;
import com.apiproject.ordersandnotificationsmanagement.products.repos.CategoriesRepo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
@Getter
@Setter
@AllArgsConstructor
public class CategoriesService {
    CategoriesRepo categoriesRepo;
    public int getCategoryRemainingProductsCount(Category category){
        return categoriesRepo.getCategoryRemainingProductsCount(category);
    }
    public void addProduct(Product product){
        categoriesRepo.addProduct(product);
    }
}
