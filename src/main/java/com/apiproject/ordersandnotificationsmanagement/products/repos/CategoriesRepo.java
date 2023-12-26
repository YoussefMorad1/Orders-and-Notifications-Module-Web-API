package com.apiproject.ordersandnotificationsmanagement.products.repos;

import com.apiproject.ordersandnotificationsmanagement.products.enums.Category;
import com.apiproject.ordersandnotificationsmanagement.products.models.Product;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;

@Getter
@Setter
@Repository
public class CategoriesRepo {
    private HashMap<Category, ArrayList<Product>> categoryProducts;
    CategoriesRepo() {
        categoryProducts = new HashMap<>();
    }
    public void addProduct(Product product) {
        Category category = product.getCategory();
        if (categoryProducts.containsKey(category)) {
            if (categoryProducts.get(category).contains(product)) {
                return;
            }
            categoryProducts.get(category).add(product);
        } else {
            ArrayList<Product> products = new ArrayList<>();
            products.add(product);
            categoryProducts.put(category, products);
        }
    }
    public int getCategoryRemainingProductsCount(Category category) {
        if (categoryProducts.containsKey(category)) {
            return categoryProducts.get(category).size();
        }
        return 0;
    }
}
