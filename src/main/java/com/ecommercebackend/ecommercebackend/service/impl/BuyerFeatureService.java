package com.ecommercebackend.ecommercebackend.service.impl;

import com.ecommercebackend.ecommercebackend.db.entity.*;
import com.ecommercebackend.ecommercebackend.db.repo.*;
import com.ecommercebackend.ecommercebackend.pojo.request.*;
import com.ecommercebackend.ecommercebackend.pojo.response.*;
import com.ecommercebackend.ecommercebackend.service.interfaces.BuyerFeatureInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
    @Autowired
    AuthRepository authRepository;


    @Override
    public BuyerFeatureResponse editProfile(BuyerFeatureRequest request) throws Exception {
        Optional<Auth> auth = Optional.ofNullable(authRepository.findByToken(request.token));
        if(!auth.isPresent() || !auth.get().isActive) {
            throw new Exception("Please log in first");
        }
        Optional<User> user = usersRepository.findById(auth.get().userId);
        if(user.get().type.equals("seller")) {
            throw new Exception("You(seller) don't have the permission");
        }

        if(user.isPresent()) {
            user.get().imageUrl = request.imageURL;
            user.get().company = request.company;
            user.get().address = request.address;
            user.get().email = request.email;
            user.get().balance = request.balance;
            user.get().name = request.name;
            usersRepository.save(user.get());

            BuyerFeatureResponse response = new BuyerFeatureResponse();
            response.dbId = user.get().id;
            response.statusCode = 200;
            response.message = request.name + "'s profile updated successfully";
            return response;
        } else {
            throw new Exception("Invalid Credential");
        }
    }

    @Override
    public PasswordChangeResponse changePassword(PasswordChangeRequest request) throws Exception{
        Optional<Auth> auth = Optional.ofNullable(authRepository.findByToken(request.token));
        if(!auth.isPresent() || !auth.get().isActive) {
            throw new Exception("Please log in first");
        }

        Optional<User> user = usersRepository.findById(auth.get().userId);
        if(user.get().type.equals("seller")) {
            throw new Exception("You(seller) don't have the permission");
        }

        if(user.isPresent()) {
            if(!user.get().password.equals(request.currentPassword)) {
                throw new Exception("Invalid Credential");
            }else{
                user.get().password = request.newPassword;
                usersRepository.save(user.get());
                auth.get().isActive = false;
                authRepository.save(auth.get());
                PasswordChangeResponse response = new PasswordChangeResponse();
                response.message = "Your password has been changed successfully" ;
                response.statusCode = 200;
                return response;
            }
        }else {
            throw new Exception("Invalid Credentail");
        }
    }
    @Override
    public ProductResponse ratingProduct(ProductRatingRequest request) throws Exception{
        Optional<Auth> auth = Optional.ofNullable(authRepository.findByToken(request.token));
        if(!auth.isPresent() || !auth.get().isActive) {
            throw new Exception("Please log in first");
        }
        Optional<User> user = usersRepository.findById(auth.get().userId);
        if(!user.isPresent()) {
            throw new Exception("Invalid credential");
        }
        if(user.get().type.equals("seller")) {
            throw new Exception("You(seller) don't have the permission");
        }

        List<ProductRating> productRatingList =
                (List<ProductRating>) productRatingsRepository.findAll();

        System.out.println("User id: " + user.get().id);
        for(ProductRating pr: productRatingList) {
            if(pr.product_id == request.productId &&
                pr.user_id == user.get().id) {
                throw new Exception("You are already rated the product");
            }
        }
        ProductRating rating = new ProductRating();
        rating.product_id = request.productId;
        rating.user_id = user.get().id;
        rating.rating = request.rating;
        productRatingsRepository.save(rating);

        productRatingList = (List<ProductRating>) productRatingsRepository.findAll();

        int id = request.productId;
        Optional<Product> product = productRepository.findById(id);
        Double sumOfRating = 0.0;
        int totalGivenRating = 0;
        for(ProductRating productRating: productRatingList) {
            if(productRating.product_id == request.productId) {
                totalGivenRating += 1;
                sumOfRating += productRating.rating;
            }
        }

        Double rat = sumOfRating / totalGivenRating ;
        rat = Math.round(rat * 100.0) / 100.0 ;
        product.get().rating = rat;

        productRepository.save(product.get());

        ProductResponse response = new ProductResponse();
        response.statusCode = 200;
        response.message = "You are successfully rated product id - " + id;
        response.dbId = id;
        return response;
    }

    public BankAccountResponse addBankAccount(BankAccountRequest request) throws Exception{
        Optional<Auth> auth = Optional.ofNullable(authRepository.findByToken(request.token));
        if(!auth.isPresent() || !auth.get().isActive) {
            throw new Exception("Please log in first");
        }
        Optional<User> user = usersRepository.findById(auth.get().userId);
        if(!user.isPresent()) {
            throw new Exception("Invalid Credential");
        }
        if(user.get().type.equals("seller")) {
            throw new Exception("You(seller) don't have the permission");
        }

        List<BankAccount> bankAccountList =
                (List<BankAccount>) bankAccountRepository.findAll();

        for(BankAccount account: bankAccountList) {
            if(account.userId.intValue() == user.get().id.intValue() && account.bankName.equals(request.bankName)
                    && account.bankBranch.equals(request.bankBranch)) {
                throw new Exception("You are already connect with this bank");
            }
        }
        BankAccount bankAccount = new BankAccount();
        bankAccount.userId = user.get().id;
        bankAccount.bankName = request.bankName;
        bankAccount.bankBranch = request.bankBranch;
        bankAccountRepository.save(bankAccount);

        BankAccountResponse response = new BankAccountResponse();
        response.statusCode = 425;
        response.message = "You are now connect with this bank";
        return response;
    }

    public ChangeMoneyResponse addMoney(ChangeMoneyRequest request) throws Exception{
        Optional<Auth> auth = Optional.ofNullable(authRepository.findByToken(request.token));
        if(!auth.isPresent() || !auth.get().isActive) {
            throw new Exception("Please log in first");
        }
        Optional<User> user = usersRepository.findById(auth.get().userId);
        if(!user.isPresent()) {
            throw new Exception("Invalide Credential");
        }
        if(user.get().type.equals("seller")) {
            throw new Exception("Sellers have no permission to add money in account");
        }

        List<BankAccount> bankAccountList =
                (List<BankAccount>) bankAccountRepository.findAll();


        for(BankAccount bankAccount: bankAccountList) {
            if(bankAccount.userId == user.get().id && bankAccount.bankName.equals(request.bankName)
                    && bankAccount.bankBranch.equals(request.bankBranch)) {
                user = Optional.ofNullable(usersRepository.findById(user.get().id.intValue()));
                Double newBalance = user.get().balance + request.balance;
                user.get().balance = newBalance;
                usersRepository.save(user.get());

                ChangeMoneyResponse response = new ChangeMoneyResponse();
                response.statusCode = 200;
                response.message = "Added money from bank to " + user.get().name + "'s account";
                return response;
            }
        }
        throw new Exception("Your given information is wrong\\nMoney do not added successfully");
    }

    public BuyProductResponse buyProduct(BuyProductRequest request) throws Exception{
        Optional<Auth> auth = Optional.ofNullable(authRepository.findByToken(request.token));
        if(!auth.isPresent() || !auth.get().isActive) {
            throw new Exception("Please log in first");
        }
        Optional<User> user = usersRepository.findById(auth.get().userId);

        if(!user.isPresent()) {
            throw new Exception("Invalide Credential");
        }
        if(user.get().type.equals("seller")) {
            throw new Exception("Sellers have no permission to buy a product");
        }

        Optional<Product> tempProduct = productRepository.findById(request.productId);
        User seller = usersRepository.findById(tempProduct.get().sellerId.intValue());

        System.out.println(user.get().id + " " + tempProduct.get().id + " " + seller.id);

        int totalPrice = request.quantity * tempProduct.get().price;
        // if users balance is greater equal than the request quantity
        // multiplied by requested product price
        if(user.get().balance < totalPrice) {
            throw new Exception("Insufficient balance");
        }
        if(tempProduct.get().quantity < request.quantity) {
            throw new Exception("Insufficient quantity");
        }

        ProductPurchase productPurchase = new ProductPurchase();
        productPurchase.product_id = request.productId;
        productPurchase.buyer_id = user.get().id;
        productPurchase.quantity = request.quantity;
        productPurchase.purchasePrice = totalPrice;
        productPurchaseRepository.save(productPurchase);

        user.get().balance -= totalPrice;
        usersRepository.save(user.get());
        seller.balance += totalPrice;
        usersRepository.save(seller);
        tempProduct.get().quantity -= request.quantity;
        productRepository.save(tempProduct.get());


        BuyProductResponse response = new BuyProductResponse();
        response.statusCode = 200;
        response.message = "Product purchases successfully";
        return response;
    }

    public List<ProductPurchase> allPurchases(ProductSellAndPurchaseRequest request) throws Exception{
        Optional<Auth> auth = Optional.ofNullable(authRepository.findByToken(request.token));
        if(!auth.isPresent() || !auth.get().isActive) {
            throw new Exception("Please log in first");
        }
        Optional<User> user = usersRepository.findById(auth.get().userId);
        if(user.get().type.equals("seller")) {
            throw new Exception("You(seller) don't have the permission");
        }
        return (List<ProductPurchase>) productPurchaseRepository.findAll();
    }
    public List<ProductPurchase> buyerPurchaseList(ProductSellAndPurchaseRequest request) throws Exception{
        Optional<Auth> auth = Optional.ofNullable(authRepository.findByToken(request.token));
        if(!auth.isPresent() || !auth.get().isActive) {
            throw new Exception("Please log in first");
        }
        Optional<User> user = usersRepository.findById(auth.get().userId);
        if(user.get().type.equals("seller")) {
            throw new Exception("You(seller) don't have the permission");
        }
        List<ProductPurchase> productPurchasesListByBuyer = new ArrayList<>();
        List<ProductPurchase> productPurchasesList = (List<ProductPurchase>)productPurchaseRepository.findAll();

        for(ProductPurchase purchase: productPurchasesList) {
            if(purchase.buyer_id == auth.get().userId) productPurchasesListByBuyer.add(purchase);
        }
        return productPurchasesListByBuyer;
    }
}
