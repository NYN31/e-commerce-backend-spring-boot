package com.ecommercebackend.ecommercebackend.controller;

import com.ecommercebackend.ecommercebackend.db.entity.Product;
import com.ecommercebackend.ecommercebackend.db.entity.ProductPurchase;
import com.ecommercebackend.ecommercebackend.pojo.request.*;
import com.ecommercebackend.ecommercebackend.pojo.response.*;
import com.ecommercebackend.ecommercebackend.service.impl.SellerFeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class SellerFeatureController {
        /*
        System.out.println("All seller feature...!") ;
        System.out.println("1. Product operation"); // done
        System.out.println("2. View products"); // done
        System.out.println("3. Connect with bank"); // done
        System.out.println("4. Check sell history"); // done
        System.out.println("5. Withdraw money from account"); // done
        System.out.println("6. Edit profile of seller"); // done
        */

    @Autowired
    SellerFeatureService sellerFeatureService;

    // 1. Product Operation [This operation has already done at product controller]

    // 2. view products [This operation also has already done at producct controller]

    // 3. Connect with bank
    @PostMapping("/connectbank")
    public BankAccountResponse connectWithBank(@RequestBody BankAccountRequest request) {
        return sellerFeatureService.addBankAccount(request);
    }

    // 4. Check sell histroy of seller
    @GetMapping("/sells")
    public List<ProductPurchase> purchasesList() {
        return sellerFeatureService.allSells();
    }
    @GetMapping("/sells/{id}")
    public List<ProductPurchase> purchasesListByUser(@PathVariable int id){
        return sellerFeatureService.sellerSellList(id);
    }

    // 5. Withdraw money from account
    @PutMapping("/withdraw")
    public changeMoneyResponse addMoneyToAccount(@RequestBody changeMoneyRequest request){
        return sellerFeatureService.withdrawMoney(request);
    }

    // 6. Edit profile
    @PutMapping("/update-seller-profile")
    public SellerFeatureResponse editProfile(@RequestBody SellerFeatureRequest request){
        return sellerFeatureService.editProfile(request);
    }
    @PutMapping("/change-seller-password")
    public PasswordChangeResponse changePassword(@RequestBody PasswordChangeRequest request){
        return sellerFeatureService.changePassword(request);
    }


}
