package com.ecommercebackend.ecommercebackend.service.impl;

import com.ecommercebackend.ecommercebackend.db.entity.*;
import com.ecommercebackend.ecommercebackend.db.repo.*;
import com.ecommercebackend.ecommercebackend.pojo.request.*;
import com.ecommercebackend.ecommercebackend.pojo.response.*;
import com.ecommercebackend.ecommercebackend.pojo.response.SellerFeatureResponse;
import com.ecommercebackend.ecommercebackend.service.interfaces.SellerFeatureInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SellerFeatureService implements SellerFeatureInterface {
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

    public SellerFeatureResponse editProfile(SellerFeatureRequest request) throws Exception{
        Optional<Auth> auth = Optional.ofNullable(authRepository.findByToken(request.token));
        if(!auth.isPresent() || !auth.get().isActive) {
            throw new Exception("Please log in first");
        }
        Optional<User> user = usersRepository.findById(auth.get().userId);
        if(user.get().type.equals("buyer")) {
            throw new Exception("You(buyer) don't have the permission");
        }

        if(user.isPresent()) {
            user.get().imageUrl = request.imageURL;
            user.get().company = request.company;
            user.get().address = request.address;
            user.get().email = request.email;
            user.get().balance = request.balance;
            user.get().name = request.name;
            usersRepository.save(user.get());

            SellerFeatureResponse response = new SellerFeatureResponse();
            response.dbId = user.get().id;
            response.statusCode = 200;
            response.message = request.name + "'s profile updated successfully";
            return response;
        } else {
            throw new Exception("Invalid Credential");
        }
    }

    public PasswordChangeResponse changePassword(PasswordChangeRequest request) throws Exception{
        Optional<Auth> auth = Optional.ofNullable(authRepository.findByToken(request.token));
        if(!auth.isPresent() || !auth.get().isActive) {
            throw new Exception("Please log in first");
        }

        Optional<User> user = usersRepository.findById(auth.get().userId);
        if(user.get().type.equals("buyer")) {
            throw new Exception("You(buyer) don't have the permission");
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
    public BankAccountResponse addBankAccount(BankAccountRequest request) throws Exception{
        Optional<Auth> auth = Optional.ofNullable(authRepository.findByToken(request.token));
        if(!auth.isPresent() || !auth.get().isActive) {
            throw new Exception("Please log in first");
        }
        Optional<User> user = usersRepository.findById(auth.get().userId);
        if(!user.isPresent()) {
            throw new Exception("Invalid Credential");
        }
        if(user.get().type.equals("buyer")) {
            throw new Exception("You(buyer) don't have the permission");
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

    public ChangeMoneyResponse withdrawMoney(ChangeMoneyRequest request) throws Exception{
        Optional<Auth> auth = Optional.ofNullable(authRepository.findByToken(request.token));
        if(!auth.isPresent() || !auth.get().isActive) {
            throw new Exception("Please log in first");
        }
        Optional<User> user = usersRepository.findById(auth.get().userId);
        if(!user.isPresent()) {
            throw new Exception("Invalide Credential");
        }
        if(user.get().type.equals("buyer")) {
            throw new Exception("You(buyer) don't have the permission");
        }

        List<BankAccount> bankAccountList =
                (List<BankAccount>) bankAccountRepository.findAll();

        for(BankAccount bankAccount: bankAccountList) {
            if(bankAccount.userId.intValue() == user.get().id && bankAccount.bankName.equals(request.bankName)
                    && bankAccount.bankBranch.equals(request.bankBranch)) {
                if(user.get().balance < request.balance) {
                    throw new Exception("Insufficient balance");
                }
                Double newBalance = user.get().balance - request.balance;
                user.get().balance = newBalance;
                usersRepository.save(user.get());

                ChangeMoneyResponse response = new ChangeMoneyResponse();
                response.dbId = user.get().id;
                response.statusCode = 200;
                response.message = "Withdrawn money from " + user.get().name + "'s account to bank";
                return response;
            }
        }
        throw new Exception("Your given information is wrong");
    }

    public List<ProductPurchase> sellerSellList(ProductSellAndPurchaseRequest request) throws Exception {
        Optional<Auth> auth = Optional.ofNullable(authRepository.findByToken(request.token));
        if(!auth.isPresent() || !auth.get().isActive) {
            throw new Exception("Please log in first");
        }
        Optional<User> user = usersRepository.findById(auth.get().userId);
        if(user.get().type.equals("buyer")) {
            throw new Exception("You(buyer) don't have the permission");
        }
        List<ProductPurchase> productSellListBySeller = new ArrayList<>();
        List<ProductPurchase> productPurchasesList = (List<ProductPurchase>)productPurchaseRepository.findAll();

        for(ProductPurchase purchase: productPurchasesList) {
            Optional<Product> product = productRepository.findById(purchase.product_id);
            if(!product.isPresent()) { continue; }
            if(product.get().sellerId == auth.get().userId) productSellListBySeller.add(purchase);
        }
        return productSellListBySeller;
    }

}
