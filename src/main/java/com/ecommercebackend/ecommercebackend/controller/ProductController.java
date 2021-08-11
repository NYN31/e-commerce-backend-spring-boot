package com.ecommercebackend.ecommercebackend.controller;

import com.ecommercebackend.ecommercebackend.entities.ProductDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class ProductController {
    private List<ProductDetails> productList = new ArrayList<>() ;

    // adding product
    @PostMapping("/addproduct")
    public List<ProductDetails> addProduct(@RequestBody ProductDetails product) {
        productList.add(product) ;
        return productList ;
    }

    // getting all products
    @GetMapping("/allproducts")
    public List<ProductDetails> getAllProducts() {
        productList = allProducts() ;
        return productList ;
    }

    private List<ProductDetails> allProducts() {
        productList.add(new ProductDetails(1, 1, "pen", "educational", 5, 200, 4.75)) ;
        return productList ;
    }
}
