package com.ecommercebackend.ecommercebackend.service.impl;

import com.ecommercebackend.ecommercebackend.db.entity.Auth;
import com.ecommercebackend.ecommercebackend.db.entity.Product;
import com.ecommercebackend.ecommercebackend.db.entity.User;
import com.ecommercebackend.ecommercebackend.db.repo.AuthRepository;
import com.ecommercebackend.ecommercebackend.db.repo.ProductPurchaseRepository;
import com.ecommercebackend.ecommercebackend.db.repo.ProductRepository;
import com.ecommercebackend.ecommercebackend.db.repo.UsersRepository;
import com.ecommercebackend.ecommercebackend.pojo.request.ProductCrudRequest;
import com.ecommercebackend.ecommercebackend.pojo.request.ProductRequest;
import com.ecommercebackend.ecommercebackend.pojo.response.ProductResponse;
import com.ecommercebackend.ecommercebackend.service.interfaces.ProductInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements ProductInterface {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    AuthRepository authRepository;
    @Autowired
    UsersRepository usersRepository;

    // getting all products
    public List<Product> allProducts() throws Exception{
        List<Product> products = (List<Product>) productRepository.findAll();
        if(products.size() == 0) {
            throw new Exception("There is no product in the store");
        }
        return products;
    }

    // adding product
    public ProductResponse addProduct(ProductRequest request) throws Exception{
        Optional<Auth> auth = Optional.ofNullable(authRepository.findByToken(request.token));
        if(!auth.isPresent() || !auth.get().isActive) {
            throw new Exception("Please log in first");
        }

        Optional<User> user = usersRepository.findById(auth.get().userId);
        if(user.get().type.equals("buyer")) {
            throw new Exception("You(buyer) don't have the permission to add a product");
        }

        Product product = productRepository.findByName(request.name);
        if(product == null) {
            product = new Product();
            product.name = request.name;
            product.price = request.price;
            product.quantity = request.quantity;
            product.rating = request.rating;
            product.sellerId = request.sellerId;
            product.tag = request.tag;
            productRepository.save(product);

            ProductResponse response = new ProductResponse() ;
            response.dbId = product.id;
            response.statusCode = 200;
            response.message = "product added successfully";
            return response ;
        }
        else {
            throw new Exception("Product is already exist");
        }
    }
    public Product findProductById(int id) {
        Optional<Product> product = productRepository.findById(id);
        if(!product.isPresent()) return null;
        else return product.get() ;
    }


    public ProductResponse updateProduct(ProductRequest request) throws Exception{
        Optional<Auth> auth = Optional.ofNullable(authRepository.findByToken(request.token));
        if(!auth.isPresent() || !auth.get().isActive) {
            throw new Exception("Please log in first");
        }

        Optional<User> user = usersRepository.findById(auth.get().userId);
        if(user.get().type.equals("buyer")) {
            throw new Exception("You(buyer) don't have the permission");
        }


        Product product = productRepository.findByName(request.name);
        if(product.sellerId.intValue() != user.get().id.intValue()) {
            throw new Exception("You do not have permission");
        }
        if(product != null) {
            product.name = request.name;
            product.price = request.price;
            product.quantity = request.quantity;
            product.rating = request.rating;
            product.sellerId = request.sellerId;
            product.tag = request.tag;
            productRepository.save(product);

            ProductResponse response = new ProductResponse() ;
            response.dbId = product.id;
            response.statusCode = 200;
            response.message = "product updated successfully";
            return response ;

        }
        else {
            throw new Exception("Product not found in the store");
        }
    }
    public ProductResponse deleteProduct(int id, String token) throws Exception{
        Optional<Auth> auth = Optional.ofNullable(authRepository.findByToken(token));
        if(!auth.isPresent() || !auth.get().isActive) {
            throw new Exception("Please log in first");
        }

        Optional<User> user = usersRepository.findById(auth.get().userId);
        if(user.get().type.equals("buyer")) {
            throw new Exception("You(buyer) don't have the permission");
        }
        Optional<Product> product = productRepository.findById(id);
        if(!product.isPresent()) {
            throw new Exception("Product is not found");
        }
        if(product.get().sellerId != user.get().id) {
            throw new Exception("You have no permission to delete this product");
        }

        productRepository.deleteById(id);
        ProductResponse response = new ProductResponse() ;
        response.statusCode = 200;
        response.message = "Successfully deleted product id - " + id;
        return response ;
    }
}
