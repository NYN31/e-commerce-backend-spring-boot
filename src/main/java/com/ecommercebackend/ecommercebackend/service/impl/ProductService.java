package com.ecommercebackend.ecommercebackend.service.impl;

import com.ecommercebackend.ecommercebackend.db.entity.Product;
import com.ecommercebackend.ecommercebackend.db.repo.ProductRepository;
import com.ecommercebackend.ecommercebackend.pojo.request.ProductRequest;
import com.ecommercebackend.ecommercebackend.pojo.response.ProductResponse;
import com.ecommercebackend.ecommercebackend.service.interfaces.ProductInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements ProductInterface {
    @Autowired
    ProductRepository productRepository;

    // getting all products
    public List<Product> allProducts() {
        return (List<Product>) productRepository.findAll();
    }

    // adding product
    public ProductResponse addProduct(ProductRequest request){
        Product product = productRepository.findByName(request.name);
        if(product == null) {
            product = new Product();
            product.name = request.name;
            product.price = request.price;
            product.quantity = request.quantity;
            product.rating = request.rating;
            product.seller_id = request.seller_id;
            product.tag = request.tag;
            productRepository.save(product);

            ProductResponse response = new ProductResponse() ;
            response.dbId = product.id;
            response.statusCode = 200;
            response.message = "product added successfully";
            return response ;
        }
        else {
            ProductResponse response = new ProductResponse() ;
            response.dbId = product.id;
            response.statusCode = 425;
            response.message = "product is already exist";
            return response ;
        }
    }
    public Product findProductById(int id)  {
        Product product = (Product) productRepository.findById(id);
        return product ;
    }


    public ProductResponse updateProduct(ProductRequest request) {
        Product product = productRepository.findByName(request.name);
        if(product != null) {
            product.name = request.name;
            product.price = request.price;
            product.quantity = request.quantity;
            product.rating = request.rating;
            product.seller_id = request.seller_id;
            product.tag = request.tag;
            productRepository.save(product);

            ProductResponse response = new ProductResponse() ;
            response.dbId = product.id;
            response.statusCode = 200;
            response.message = "product updated successfully";
            return response ;

        }
        else {
            ProductResponse response = new ProductResponse() ;
            response.statusCode = 425;
            response.message = "product is not found";
            return response ;
        }
    }
    public ProductResponse deleteProduct(int id) {
        productRepository.deleteById(id);
        ProductResponse response = new ProductResponse() ;
        response.dbId = id;
        response.statusCode = 200;
        response.message = "Successfully deleted product id - " + id;
        return response ;
    }
}
