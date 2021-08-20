package com.ecommercebackend.ecommercebackend.service.interfaces;

import com.ecommercebackend.ecommercebackend.db.entity.Product;
import com.ecommercebackend.ecommercebackend.pojo.request.ProductCrudRequest;
import com.ecommercebackend.ecommercebackend.pojo.request.ProductRequest;
import com.ecommercebackend.ecommercebackend.pojo.response.ProductResponse;

import java.util.*;

public interface ProductInterface {
    ProductResponse addProduct(ProductRequest request) throws Exception;
    List<Product> allProducts() throws Exception;
    Product findProductById(int id);
    ProductResponse updateProduct(ProductRequest request) throws Exception;
    ProductResponse deleteProduct(int id, String token) throws Exception;
}
