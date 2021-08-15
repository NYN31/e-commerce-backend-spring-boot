package com.ecommercebackend.ecommercebackend.service.interfaces;

import com.ecommercebackend.ecommercebackend.db.entity.Product;
import com.ecommercebackend.ecommercebackend.pojo.request.BuyerFeatureRequest;
import com.ecommercebackend.ecommercebackend.pojo.request.PasswordChangeRequest;
import com.ecommercebackend.ecommercebackend.pojo.request.ProductRatingRequest;
import com.ecommercebackend.ecommercebackend.pojo.request.ProductRequest;
import com.ecommercebackend.ecommercebackend.pojo.response.BuyerFeatureResponse;
import com.ecommercebackend.ecommercebackend.pojo.response.PasswordChangeResponse;
import com.ecommercebackend.ecommercebackend.pojo.response.ProductResponse;

import java.util.*;

public interface BuyerFeatureInterface {
    BuyerFeatureResponse editProfile(BuyerFeatureRequest request);
    PasswordChangeResponse changePassword(PasswordChangeRequest request);
    ProductResponse ratingProduct(ProductRatingRequest request);
}
