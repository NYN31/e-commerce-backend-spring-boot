package com.ecommercebackend.ecommercebackend.controller;

import com.ecommercebackend.ecommercebackend.db.entity.BankAccount;
import com.ecommercebackend.ecommercebackend.db.entity.ProductPurchase;
import com.ecommercebackend.ecommercebackend.pojo.request.*;
import com.ecommercebackend.ecommercebackend.pojo.response.*;
import com.ecommercebackend.ecommercebackend.service.impl.BuyerFeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BuyerFeatureController {

    /*
        System.out.println("All Buyer feature...!") ;
        System.out.println("1. Edit profile"); // done, without picture db
        System.out.println("2. View products"); // done
        System.out.println("3. Buy a product"); // pending(facing problems)
        System.out.println("4. Rate a product"); // done
        System.out.println("5. Check purchase history"); // done
        System.out.println("6. Connect bank account"); // done
        System.out.println("7. Add money"); // done
        System.out.println("8. Sign out"); //

*/
    @Autowired
    BuyerFeatureService buyerFeatureService;



    // 1. Edit profile
    @PutMapping("/update-buyer-profile")
    public BuyerFeatureResponse editProfile(@RequestBody BuyerFeatureRequest request){
        return buyerFeatureService.editProfile(request);
    }
    @PutMapping("/change-buyer-password")
    public PasswordChangeResponse changePassword(@RequestBody PasswordChangeRequest request){
        return buyerFeatureService.changePassword(request);
    }

    // 2. show all products [Already implemented at product controller]

    // 3. buy a product
    @PostMapping("/buy-a-product")
    public BuyProductResponse buyProduct(@RequestBody BuyProductRequest request){
        return buyerFeatureService.buyProduct(request);
    }

    // 4. rate a product
    @PostMapping("/add-rating")
    public ProductResponse addRating(@RequestBody ProductRatingRequest request) {
        return buyerFeatureService.ratingProduct(request);
    }

    // 5. check purchase history of a seller
    @GetMapping("/purchases")
    public List<ProductPurchase> purchasesList() {
        return buyerFeatureService.allPurchases();
    }

    @GetMapping("/purchases/{id}")
    public List<ProductPurchase> purchasesListByUser(@PathVariable int id){
        return buyerFeatureService.buyerPurchaseList(id);
    }

    // 6. Connect bank account
    @PostMapping("/connect-bank-as-buyer")
    public BankAccountResponse connectWithBank(@RequestBody BankAccountRequest request) {
        return buyerFeatureService.addBankAccount(request);
    }
    @GetMapping("/banks")
    public List<BankAccount> allBankAccount() {
        return buyerFeatureService.findAllBankAccounts();
    }

    // 7. add money to account from the bank
    @PutMapping("/add-money")
    public changeMoneyResponse addMoneyToAccount(@RequestBody changeMoneyRequest request){
        return buyerFeatureService.addMoney(request);
    }

}
