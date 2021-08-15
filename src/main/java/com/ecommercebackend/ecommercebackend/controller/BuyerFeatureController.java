package com.ecommercebackend.ecommercebackend.controller;

import com.ecommercebackend.ecommercebackend.db.entity.Product;
import com.ecommercebackend.ecommercebackend.pojo.request.BuyerFeatureRequest;
import com.ecommercebackend.ecommercebackend.pojo.request.PasswordChangeRequest;
import com.ecommercebackend.ecommercebackend.pojo.request.ProductRatingRequest;
import com.ecommercebackend.ecommercebackend.pojo.response.BuyerFeatureResponse;
import com.ecommercebackend.ecommercebackend.pojo.response.PasswordChangeResponse;
import com.ecommercebackend.ecommercebackend.pojo.response.ProductResponse;
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
        System.out.println("3. Buy a product"); // done
        System.out.println("4. Rate a product"); // done
        System.out.println("5. Check purchase history"); // done
        System.out.println("6. Connect bank account"); // done
        System.out.println("7. Add money"); // done
        System.out.println("8. Sign out"); // done

*/
    @Autowired
    BuyerFeatureService buyerFeatureService;


    // 1. Edit profile
    @PutMapping("/update-profile")
    public BuyerFeatureResponse editProfile(@RequestBody BuyerFeatureRequest request){
        return buyerFeatureService.editProfile(request);
    }
    @PutMapping("/change-password")
    public PasswordChangeResponse changePassword(@RequestBody PasswordChangeRequest request){
        return buyerFeatureService.changePassword(request);
    }

    // 2. show all products [Already implemented at product controller]

    // 3. buy a product

    // 4. rate a product
    @PostMapping("/add-rating")
    public ProductResponse addRating(@RequestBody ProductRatingRequest request) {
        return buyerFeatureService.ratingProduct(request);
    }

    // 5. check purchase history of a seller

    // 6. Connect bank account

    // 7. add money to account from the bank

}
