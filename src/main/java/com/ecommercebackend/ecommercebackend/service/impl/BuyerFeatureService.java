package com.ecommercebackend.ecommercebackend.service.impl;

import com.ecommercebackend.ecommercebackend.db.entity.Product;
import com.ecommercebackend.ecommercebackend.db.entity.ProductRating;
import com.ecommercebackend.ecommercebackend.db.entity.User;
import com.ecommercebackend.ecommercebackend.db.repo.ProductRatingsRepository;
import com.ecommercebackend.ecommercebackend.db.repo.ProductRepository;
import com.ecommercebackend.ecommercebackend.db.repo.UsersRepository;
import com.ecommercebackend.ecommercebackend.pojo.request.BuyerFeatureRequest;
import com.ecommercebackend.ecommercebackend.pojo.request.PasswordChangeRequest;
import com.ecommercebackend.ecommercebackend.pojo.request.ProductRatingRequest;
import com.ecommercebackend.ecommercebackend.pojo.request.ProductRequest;
import com.ecommercebackend.ecommercebackend.pojo.response.BuyerFeatureResponse;
import com.ecommercebackend.ecommercebackend.pojo.response.PasswordChangeResponse;
import com.ecommercebackend.ecommercebackend.pojo.response.ProductResponse;
import com.ecommercebackend.ecommercebackend.service.interfaces.BuyerFeatureInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BuyerFeatureService implements BuyerFeatureInterface {
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductRatingsRepository productRatingsRepository;

    @Override
    public BuyerFeatureResponse editProfile(BuyerFeatureRequest request) {
        User user = usersRepository.findByEmail(request.email);

        if(user != null) {
            user.type = request.type;
            user.imageUrl = request.imageURL;
            user.company = request.company;
            user.address = request.address;
            user.email = request.email;
            user.balance = request.balance;
            user.name = request.name;
            usersRepository.save(user);

            BuyerFeatureResponse response = new BuyerFeatureResponse();
            response.dbId = user.id;
            response.statusCode = 200;
            response.message = request.name + "'s profile updated successfully";
            return response;
        } else {
            BuyerFeatureResponse response = new BuyerFeatureResponse();
            response.statusCode = 425;
            response.message = "User not found, select right email pls";
            return response;
        }
    }

    @Override
    public PasswordChangeResponse changePassword(PasswordChangeRequest request) {
        //System.out.println(request.email);
        User user = usersRepository.findByEmail(request.email);
        //System.out.println(user.email + " " + request.email);
        String msg = "";
        if(user != null) {
            if(user != null && !user.name.equals(request.name)) { msg += "Invalid name ";}
            if(user != null && !user.password.equals(request.currentPassword)) { msg += "invalid password.";}
            if(msg.length() > 0) {
                PasswordChangeResponse response = new PasswordChangeResponse();
                response.message = msg ;
                response.statusCode = 425;
                return response;
            }else {
                user.password = request.newPassword;
                usersRepository.save(user);

                PasswordChangeResponse response = new PasswordChangeResponse();
                response.message = "Your password has been changed successfully" ;
                response.statusCode = 200;
                return response;
            }

        }else {
            PasswordChangeResponse response = new PasswordChangeResponse();
            response.message = "Invalid email" ;
            response.statusCode = 425;
            return response;
        }
    }
    @Override
    public ProductResponse ratingProduct(ProductRatingRequest request) {
        List<ProductRating> productRatingList =
                (List<ProductRating>) productRatingsRepository.findAll();
        for(ProductRating pr: productRatingList) {
            if(pr.product_id == request.product_id &&
                pr.user_id == request.user_id) {
                ProductResponse response = new ProductResponse();
                response.statusCode = 425;
                response.message = "You are already rated product";
                response.dbId = pr.product_id;
                return response;
            }
        }
        ProductRating rating = new ProductRating();
        rating.product_id = request.product_id;
        rating.user_id = request.user_id;
        rating.rating = request.rating;
        productRatingsRepository.save(rating);

        productRatingList = (List<ProductRating>) productRatingsRepository.findAll();

        int id = request.product_id;
        Product product = productRepository.findById(id);
        Double sumOfRating = 0.0;
        int totalGivenRating = 0;
        for(ProductRating productRating: productRatingList) {
            if(productRating.product_id == request.product_id) {
                totalGivenRating += 1;
                sumOfRating += productRating.rating;
            }
        }
        System.out.println(sumOfRating + " " + totalGivenRating);
        Double rat = sumOfRating / totalGivenRating ;
        rat = Math.round(rat * 100.0) / 100.0 ;
        product.rating = rat;

        productRepository.save(product);
        System.out.println("product rating: " + product.rating);
        ProductResponse response = new ProductResponse();
        response.statusCode = 200;
        response.message = "You are successfully rated product id - " + id;
        response.dbId = id;
        return response;
    }
}
