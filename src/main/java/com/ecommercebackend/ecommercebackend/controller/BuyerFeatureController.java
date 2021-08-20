package com.ecommercebackend.ecommercebackend.controller;

import com.ecommercebackend.ecommercebackend.db.entity.Product;
import com.ecommercebackend.ecommercebackend.db.entity.ProductPurchase;
import com.ecommercebackend.ecommercebackend.db.repo.AuthRepository;
import com.ecommercebackend.ecommercebackend.pojo.request.*;
import com.ecommercebackend.ecommercebackend.pojo.response.*;
import com.ecommercebackend.ecommercebackend.service.impl.BuyerFeatureService;
import com.ecommercebackend.ecommercebackend.service.impl.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/buyers")
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
    @Autowired
    ProductService productService;
    @Autowired
    AuthRepository authRepository;


    // 1. Edit profile
    @PutMapping("/update-profile")
    public BuyerFeatureResponse editProfile(@RequestBody BuyerFeatureRequest request) throws Exception{
        return buyerFeatureService.editProfile(request);
    }
    @PutMapping("/change-password")
    public PasswordChangeResponse changePassword(@RequestBody PasswordChangeRequest request) throws Exception{
        return buyerFeatureService.changePassword(request);
    }

    // 2. show all products [Already implemented at product controller]
    @GetMapping("/products")
    public List<Product> allProducts() throws Exception{
        return productService.allProducts() ;
    }

    // 3. buy a product
    @PostMapping("/buy-product")
    public BuyProductResponse buyProduct(@RequestBody BuyProductRequest request) throws Exception{
        return buyerFeatureService.buyProduct(request);
    }

    // 4. rate a product
    @PostMapping("/add-rating")
    public ProductResponse addRating(@RequestBody ProductRatingRequest request) throws Exception{
        return buyerFeatureService.ratingProduct(request);
    }

    // 5. check purchase history of a seller
    @GetMapping("/purchases")
    public List<ProductPurchase> purchasesList(@RequestBody ProductSellAndPurchaseRequest request) throws Exception{
        return buyerFeatureService.allPurchases(request);
    }

    @GetMapping("/purchases/{id}")
    public List<ProductPurchase> purchasesListByUser(@RequestBody ProductSellAndPurchaseRequest request) throws Exception{
        return buyerFeatureService.buyerPurchaseList(request);
    }

    // 6. Connect bank account
    @PostMapping("/connect-bank")
    public BankAccountResponse connectWithBank(@RequestBody BankAccountRequest request) throws Exception{
        return buyerFeatureService.addBankAccount(request);
    }

    // 7. add money to account from the bank
    @PutMapping("/add-money")
    public ChangeMoneyResponse addMoneyToAccount(@RequestBody ChangeMoneyRequest request) throws Exception{
        return buyerFeatureService.addMoney(request);
    }

}
