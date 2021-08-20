package com.ecommercebackend.ecommercebackend.service.interfaces;

import com.ecommercebackend.ecommercebackend.db.entity.ProductPurchase;
import com.ecommercebackend.ecommercebackend.pojo.request.*;
import com.ecommercebackend.ecommercebackend.pojo.response.*;

import java.util.List;

public interface SellerFeatureInterface {
    SellerFeatureResponse editProfile(SellerFeatureRequest request) throws Exception;
    PasswordChangeResponse changePassword(PasswordChangeRequest request) throws Exception;
    BankAccountResponse addBankAccount(BankAccountRequest request) throws Exception;
    ChangeMoneyResponse withdrawMoney(ChangeMoneyRequest request) throws Exception;
    List<ProductPurchase> sellerSellList(ProductSellAndPurchaseRequest request) throws Exception;
}
