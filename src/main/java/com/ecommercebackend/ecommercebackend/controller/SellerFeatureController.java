package com.ecommercebackend.ecommercebackend.controller;

import com.ecommercebackend.ecommercebackend.db.entity.Product;
import com.ecommercebackend.ecommercebackend.db.entity.ProductPurchase;
import com.ecommercebackend.ecommercebackend.pojo.exception.ProductNotFoundException;
import com.ecommercebackend.ecommercebackend.pojo.request.*;
import com.ecommercebackend.ecommercebackend.pojo.response.*;
import com.ecommercebackend.ecommercebackend.service.impl.ProductService;
import com.ecommercebackend.ecommercebackend.service.impl.SellerFeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/sellers")
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
    @Autowired
    ProductService productService;

    // 1. Product Operation [This operation has already done at product controller]
        /*
            i. Add product
            ii. Show product by id
            iii. Update product
            iv. Delete product
        * */
        // i. Add product
    @PostMapping(
            value = "products",
            consumes= MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ProductResponse addProduct(@RequestBody ProductRequest request) {
        return productService.addProduct(request);
    }
        // ii. Find product by product id
    @GetMapping("/products/{id}")
    public Product findProductById(@PathVariable int id) throws ProductNotFoundException {
        Product product = productService.findProductById(id) ;
        if(product != null) {
            return product ;
        } else {
            throw new ProductNotFoundException("Product id " + id + " is not found");
        }
    }

        // iii. Update product details
    @PutMapping("/products")
    public ProductResponse updateProduct(@RequestBody ProductRequest request) {
        return productService.updateProduct(request);
    }

        // iv. Delete product by id
    @DeleteMapping("/products/{id}")
    public ProductResponse deleteProduct(@PathVariable int id) {
        Product product = productService.findProductById(id);
        if(product != null) {
            return productService.deleteProduct(id);
        }
        else {
            throw new ProductNotFoundException("Product id " + id + " is not found");
        }
    }

    // 2. view products [This operation also has already done at producct controller]
    @GetMapping("/products")
    public List<Product> allProducts() {
        return productService.allProducts() ;
    }

    // 3. Connect with bank
    @PostMapping("/connect-bank")
    public BankAccountResponse connectWithBank(@RequestBody BankAccountRequest request) throws Exception{
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
    @PutMapping("/withdraw-money")
    public changeMoneyResponse addMoneyToAccount(@RequestBody changeMoneyRequest request) throws Exception{
        return sellerFeatureService.withdrawMoney(request);
    }

    // 6. Edit profile
    @PutMapping("/update-profile")
    public SellerFeatureResponse editProfile(@RequestBody SellerFeatureRequest request){
        return sellerFeatureService.editProfile(request);
    }
    @PutMapping("/update-password")
    public PasswordChangeResponse changePassword(@RequestBody PasswordChangeRequest request){
        return sellerFeatureService.changePassword(request);
    }


}
