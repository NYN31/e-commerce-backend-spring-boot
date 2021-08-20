package com.ecommercebackend.ecommercebackend.service.interfaces;

import com.ecommercebackend.ecommercebackend.db.entity.ProductPurchase;
import com.ecommercebackend.ecommercebackend.pojo.request.*;
import com.ecommercebackend.ecommercebackend.pojo.response.*;

import java.util.*;

public interface BuyerFeatureInterface {
    BuyerFeatureResponse editProfile(BuyerFeatureRequest request) throws Exception;
    PasswordChangeResponse changePassword(PasswordChangeRequest request) throws Exception;
    ProductResponse ratingProduct(ProductRatingRequest request) throws Exception;
    BankAccountResponse addBankAccount(BankAccountRequest request) throws Exception;
    ChangeMoneyResponse addMoney(ChangeMoneyRequest request) throws Exception;
    BuyProductResponse buyProduct(BuyProductRequest request) throws Exception;
    List<ProductPurchase> allPurchases(ProductSellAndPurchaseRequest request) throws Exception;
    List<ProductPurchase> buyerPurchaseList(ProductSellAndPurchaseRequest request) throws Exception;
}
