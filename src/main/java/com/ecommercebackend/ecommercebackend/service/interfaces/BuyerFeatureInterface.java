package com.ecommercebackend.ecommercebackend.service.interfaces;

import com.ecommercebackend.ecommercebackend.db.entity.BankAccount;
import com.ecommercebackend.ecommercebackend.db.entity.ProductPurchase;
import com.ecommercebackend.ecommercebackend.pojo.request.*;
import com.ecommercebackend.ecommercebackend.pojo.response.*;

import java.util.*;

public interface BuyerFeatureInterface {
    BuyerFeatureResponse editProfile(BuyerFeatureRequest request) throws Exception;
    PasswordChangeResponse changePassword(PasswordChangeRequest request);
    ProductResponse ratingProduct(ProductRatingRequest request);
    BankAccountResponse addBankAccount(BankAccountRequest request) throws Exception;
    List<BankAccount> findAllBankAccounts();
    changeMoneyResponse addMoney(changeMoneyRequest request) throws Exception;
    BuyProductResponse buyProduct(BuyProductRequest request);
    List<ProductPurchase> allPurchases();
    List<ProductPurchase> buyerPurchaseList(int id);
}
