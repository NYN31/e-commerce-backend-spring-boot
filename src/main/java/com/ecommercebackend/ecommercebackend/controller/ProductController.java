package com.ecommercebackend.ecommercebackend.controller;

import com.ecommercebackend.ecommercebackend.db.entity.Product;
import com.ecommercebackend.ecommercebackend.db.repo.ProductRepository;
import com.ecommercebackend.ecommercebackend.pojo.exception.ProductNotFoundException;
import com.ecommercebackend.ecommercebackend.pojo.request.ProductRequest;
import com.ecommercebackend.ecommercebackend.pojo.response.ProductResponse;
import com.ecommercebackend.ecommercebackend.service.impl.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    /*
        1. Add product
        2. All products
        3. Find product by id
        4. Update product
        5. Delete product by id
    */
    @Autowired
    ProductService productService;


    // 1. Adding product
    @PostMapping(
            value = "",
            consumes= MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ProductResponse addProduct(@RequestBody ProductRequest request) {
        return productService.addProduct(request);
    }

    // 2. All Products
    @GetMapping("")
    public List<Product> allProducts() {
        return productService.allProducts() ;
    }

    // 3. Find product by product id
    @GetMapping("/{id}")
    public Product findProductById(@PathVariable int id) throws ProductNotFoundException {
        Product product = productService.findProductById(id) ;
        if(product != null) {
            return product ;
        } else {
            throw new ProductNotFoundException("Product id " + id + " is not found");
        }
    }

    // 4. Update product details
    @PutMapping("")
    public ProductResponse updateProduct(@RequestBody ProductRequest request) {
        return productService.updateProduct(request);
    }

    // 5. Delete product by id
    @DeleteMapping("/{id}")
    public ProductResponse deleteProduct(@PathVariable int id) {
        Product product = productService.findProductById(id);
        if(product != null) {
            return productService.deleteProduct(id);
        }
        else {
            throw new ProductNotFoundException("Product id " + id + " is not found");
        }
    }
}
