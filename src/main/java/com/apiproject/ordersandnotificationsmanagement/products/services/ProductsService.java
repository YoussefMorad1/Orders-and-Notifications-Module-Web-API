package com.apiproject.ordersandnotificationsmanagement.products.services;

import com.apiproject.ordersandnotificationsmanagement.products.enums.Category;
import com.apiproject.ordersandnotificationsmanagement.products.enums.Vendor;
import com.apiproject.ordersandnotificationsmanagement.products.models.Product;
import com.apiproject.ordersandnotificationsmanagement.products.models.ProductItem;
import com.apiproject.ordersandnotificationsmanagement.products.repos.ProductsRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class ProductsService {
    private ProductsRepo productsRepo;
    public ArrayList<Product> getAllProducts(){
        return productsRepo.getProducts();
    }
    public ProductItem getProductItem(Product product){
        return product.getOneProductItem();
    }
    public Product getProduct(String productID){
        return productsRepo.getProductById(productID);
    }
}
