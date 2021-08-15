package com.ecommercebackend.ecommercebackend.service.interfaces;

import com.ecommercebackend.ecommercebackend.db.entity.Product;
import com.ecommercebackend.ecommercebackend.pojo.request.ProductRequest;
import com.ecommercebackend.ecommercebackend.pojo.response.ProductResponse;

import java.util.*;

public interface ProductInterface {
    ProductResponse addProduct(ProductRequest request);
    List<Product> allProducts() ;
    Product findProductById(int id);
    ProductResponse updateProduct(ProductRequest request);
    ProductResponse deleteProduct(int id);
}
