package com.ecommercebackend.ecommercebackend.service.impl;

import com.ecommercebackend.ecommercebackend.db.entity.*;
import com.ecommercebackend.ecommercebackend.db.repo.*;
import com.ecommercebackend.ecommercebackend.pojo.request.*;
import com.ecommercebackend.ecommercebackend.pojo.response.*;
import com.ecommercebackend.ecommercebackend.service.interfaces.BuyerFeatureInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class BuyerFeatureService implements BuyerFeatureInterface {
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductRatingsRepository productRatingsRepository;
    @Autowired
    BankAccountRepository bankAccountRepository;
    @Autowired
    ProductPurchaseRepository productPurchaseRepository;

    @Override
    public BuyerFeatureResponse editProfile(BuyerFeatureRequest request) {
        User user = usersRepository.findByEmail(request.email);

        if(user != null) {
            user.imageUrl = request.imageURL;
            user.company = request.company;
            user.address = request.address;
            user.email = request.email;
            user.balance = request.balance;
            user.name = request.name;
            usersRepository.save(user);

            BuyerFeatureResponse response = new BuyerFeatureResponse();
            response.dbId = user.id;
            response.statusCode = 200;
            response.message = request.name + "'s profile updated successfully";
            return response;
        } else {
            BuyerFeatureResponse response = new BuyerFeatureResponse();
            response.statusCode = 425;
            response.message = "User not found, select right email pls";
            return response;
        }
    }

    @Override
    public PasswordChangeResponse changePassword(PasswordChangeRequest request) {
        User user = usersRepository.findByEmail(request.email);

        String validationMessage = "";
        if(user != null) {
            if(user != null && !user.name.equals(request.name)) { validationMessage += "Invalid name ";}
            if(user != null && !user.password.equals(request.currentPassword)) { validationMessage += "invalid password.";}
            if(validationMessage.length() > 0) {
                PasswordChangeResponse response = new PasswordChangeResponse();
                response.message = validationMessage ;
                response.statusCode = 425;
                return response;
            }else {
                user.password = request.newPassword;
                usersRepository.save(user);

                PasswordChangeResponse response = new PasswordChangeResponse();
                response.message = "Your password has been changed successfully" ;
                response.statusCode = 200;
                return response;
            }

        }else {
            PasswordChangeResponse response = new PasswordChangeResponse();
            response.message = "Invalid email" ;
            response.statusCode = 425;
            return response;
        }
    }
    @Override
    public ProductResponse ratingProduct(ProductRatingRequest request) {
        List<ProductRating> productRatingList =
                (List<ProductRating>) productRatingsRepository.findAll();
        for(ProductRating pr: productRatingList) {
            if(pr.product_id == request.product_id &&
                pr.user_id == request.user_id) {
                ProductResponse response = new ProductResponse();
                response.statusCode = 425;
                response.message = "You are already rated product";
                response.dbId = pr.product_id;
                return response;
            }
        }
        ProductRating rating = new ProductRating();
        rating.product_id = request.product_id;
        rating.user_id = request.user_id;
        rating.rating = request.rating;
        productRatingsRepository.save(rating);

        productRatingList = (List<ProductRating>) productRatingsRepository.findAll();

        int id = request.product_id;
        Product product = productRepository.findById(id);
        Double sumOfRating = 0.0;
        int totalGivenRating = 0;
        for(ProductRating productRating: productRatingList) {
            if(productRating.product_id == request.product_id) {
                totalGivenRating += 1;
                sumOfRating += productRating.rating;
            }
        }

        Double rat = sumOfRating / totalGivenRating ;
        rat = Math.round(rat * 100.0) / 100.0 ;
        product.rating = rat;

        productRepository.save(product);
        System.out.println("product rating: " + product.rating);
        ProductResponse response = new ProductResponse();
        response.statusCode = 200;
        response.message = "You are successfully rated product id - " + id;
        response.dbId = id;
        return response;
    }

    public BankAccountResponse addBankAccount(BankAccountRequest request) {
        List<BankAccount> bankAccountList =
                (List<BankAccount>) bankAccountRepository.findAll();
        for(BankAccount account: bankAccountList) {
            if(account.email.equals(request.email) && account.bankName.equals(request.bankName)
                    && account.bankBranch.equals(request.bankBranch)) {
                BankAccountResponse response = new BankAccountResponse();
                response.statusCode = 425;
                response.message = "You are already connect with this bank";
                return response;
            }
        }
        BankAccount bankAccount = new BankAccount();
        bankAccount.email = request.email;
        bankAccount.bankName = request.bankName;
        bankAccount.bankBranch = request.bankBranch;
        bankAccountRepository.save(bankAccount);

        BankAccountResponse response = new BankAccountResponse();
        response.statusCode = 425;
        response.message = "You are now connect with this bank";
        return response;
    }
    public List<BankAccount> findAllBankAccounts() {
        return (List<BankAccount>) bankAccountRepository.findAll();
    }

    public changeMoneyResponse addMoney(changeMoneyRequest request) {
        List<BankAccount> bankAccountList =
                (List<BankAccount>) bankAccountRepository.findAll();
        for(BankAccount bankAccount: bankAccountList) {
            if(bankAccount.email.equals(request.email) && bankAccount.bankName.equals(request.bankName)
                    && bankAccount.bankBranch.equals(request.bankBranch)) {
                User user = usersRepository.findByEmail(bankAccount.email);
                Double newBalance = user.balance + request.balance;
                user.balance = newBalance;
                usersRepository.save(user);

                changeMoneyResponse response = new changeMoneyResponse();
                response.dbId = user.id;
                response.statusCode = 200;
                response.message = "Added money from bank to " + user.name + "'s account";
                return response;
            }
        }
        changeMoneyResponse response = new changeMoneyResponse();
        response.statusCode = 425;
        response.message = "Your given information is wrong";
        return response;
    }

    public BuyProductResponse buyProduct(BuyProductRequest request) {
        User buyer = usersRepository.findById((int)request.buyerId);
        User seller = usersRepository.findById((int)request.sellerId);
        Product tempProduct = productRepository.findById((int)request.productId);

        String validationMessage = "";
        int totalPrice = (int)request.quantity * (int)tempProduct.price;
        // if users balance is greater equal than the request quantity
        // multiplied by requested product price
        if(buyer.balance < totalPrice) {
            validationMessage += "$Insufficient balance ";
        }
        if(tempProduct.quantity < (int)request.quantity) {
            validationMessage += "$Insufficient quantity";
        }

        if(validationMessage.length() > 0) {
            BuyProductResponse response = new BuyProductResponse();
            response.dbProductId = request.productId;
            response.dbUserId = request.buyerId;
            response.statusCode = 425;
            response.message = validationMessage;
            return response;
        }else {
            ProductPurchase productPurchase = new ProductPurchase();
            productPurchase.product_id = request.productId;
            productPurchase.buyer_id = request.buyerId;

            productPurchase.seller_id = request.sellerId;
            productPurchase.quantity = request.quantity;
            productPurchase.price = totalPrice;
            productPurchaseRepository.save(productPurchase);

            buyer.balance -= totalPrice;
            usersRepository.save(buyer);
            seller.balance += totalPrice;
            usersRepository.save(seller);
            tempProduct.quantity -= request.quantity;
            productRepository.save(tempProduct);


            BuyProductResponse response = new BuyProductResponse();
            response.dbProductId = request.productId;
            response.dbUserId = request.buyerId;
            response.statusCode = 200;
            response.message = "Product purchases successfully";
            return response;
        }
    }

    public List<ProductPurchase> allPurchases(){
        return (List<ProductPurchase>) productPurchaseRepository.findAll();
    }
    public List<ProductPurchase> buyerPurchaseList(int id){
        List<ProductPurchase> list = new ArrayList<>();
        List<ProductPurchase> all = (List<ProductPurchase>)productPurchaseRepository.findAll();

        for(ProductPurchase purchase: all) {
            if(purchase.buyer_id == id) list.add(purchase);
        }
        return list;
    }
}
