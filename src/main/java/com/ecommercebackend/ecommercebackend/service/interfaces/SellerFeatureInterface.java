package com.ecommercebackend.ecommercebackend.service.interfaces;

import com.ecommercebackend.ecommercebackend.db.entity.BankAccount;
import com.ecommercebackend.ecommercebackend.db.entity.Product;
import com.ecommercebackend.ecommercebackend.db.entity.ProductPurchase;
import com.ecommercebackend.ecommercebackend.pojo.request.*;
import com.ecommercebackend.ecommercebackend.pojo.response.*;

import java.util.List;

public interface SellerFeatureInterface {
    SellerFeatureResponse editProfile(SellerFeatureRequest request);
    PasswordChangeResponse changePassword(PasswordChangeRequest request);
    BankAccountResponse addBankAccount(BankAccountRequest request) throws Exception;
//    List<BankAccount> findAllBankAccounts();
    changeMoneyResponse withdrawMoney(changeMoneyRequest request) throws Exception;
    List<ProductPurchase> allSells();
    List<ProductPurchase> sellerSellList(int id);
}
