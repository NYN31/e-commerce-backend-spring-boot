package com.ecommercebackend.ecommercebackend.service.impl;

import com.ecommercebackend.ecommercebackend.db.entity.BankAccount;
import com.ecommercebackend.ecommercebackend.db.entity.Product;
import com.ecommercebackend.ecommercebackend.db.entity.ProductPurchase;
import com.ecommercebackend.ecommercebackend.db.entity.User;
import com.ecommercebackend.ecommercebackend.db.repo.*;
import com.ecommercebackend.ecommercebackend.pojo.request.BankAccountRequest;
import com.ecommercebackend.ecommercebackend.pojo.request.PasswordChangeRequest;
import com.ecommercebackend.ecommercebackend.pojo.request.SellerFeatureRequest;
import com.ecommercebackend.ecommercebackend.pojo.request.changeMoneyRequest;
import com.ecommercebackend.ecommercebackend.pojo.response.*;
import com.ecommercebackend.ecommercebackend.pojo.response.SellerFeatureResponse;
import com.ecommercebackend.ecommercebackend.service.interfaces.SellerFeatureInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

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

    public SellerFeatureResponse editProfile(SellerFeatureRequest request) {
        User user = usersRepository.findByEmail(request.email);

        if(user != null) {
            user.imageUrl = request.imageURL;
            user.company = request.company;
            user.address = request.address;
            user.email = request.email;
            user.balance = request.balance;
            user.name = request.name;
            usersRepository.save(user);

            SellerFeatureResponse response = new SellerFeatureResponse();
            response.dbId = user.id;
            response.statusCode = 200;
            response.message = request.name + "'s profile updated successfully";
            return response;
        } else {
            SellerFeatureResponse response = new SellerFeatureResponse();
            response.statusCode = 425;
            response.message = "User not found, select right email pls";
            return response;
        }
    }

    public PasswordChangeResponse changePassword(PasswordChangeRequest request){
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
    public BankAccountResponse addBankAccount(BankAccountRequest request) throws Exception{
        List<BankAccount> bankAccountList =
                (List<BankAccount>) bankAccountRepository.findAll();
        User user = (User) usersRepository.findByEmail(request.email);
        if(user == null) {
            throw new Exception("Invalid Credential");
        }
        for(BankAccount account: bankAccountList) {
            if(account.userId.intValue() == user.id.intValue() && account.bankName.equals(request.bankName)
                    && account.bankBranch.equals(request.bankBranch)) {
                BankAccountResponse response = new BankAccountResponse();
                response.statusCode = 425;
                response.message = "You are already connect with this bank";
                return response;
            }
        }
        BankAccount bankAccount = new BankAccount();
        bankAccount.userId = user.id;
        bankAccount.bankName = request.bankName;
        bankAccount.bankBranch = request.bankBranch;
        bankAccountRepository.save(bankAccount);

        BankAccountResponse response = new BankAccountResponse();
        response.statusCode = 425;
        response.message = "You are now connect with this bank";
        return response;
    }

    public changeMoneyResponse withdrawMoney(changeMoneyRequest request) throws Exception{
        List<BankAccount> bankAccountList =
                (List<BankAccount>) bankAccountRepository.findAll();

        User user = (User) usersRepository.findByEmail(request.email);
        if(user == null) {
            throw new Exception("Invalide Credential");
        }

        for(BankAccount bankAccount: bankAccountList) {
            if(bankAccount.userId.intValue() == user.id && bankAccount.bankName.equals(request.bankName)
                    && bankAccount.bankBranch.equals(request.bankBranch)) {
                user = usersRepository.findById(user.id.intValue());
                if(user.balance < request.balance) {
                    changeMoneyResponse response = new changeMoneyResponse();
                    response.statusCode = 425;
                    response.message = "Insufficient balance";
                    return response;
                }
                Double newBalance = user.balance - request.balance;
                user.balance = newBalance;
                usersRepository.save(user);

                changeMoneyResponse response = new changeMoneyResponse();
                response.dbId = user.id;
                response.statusCode = 200;
                response.message = "Withdrawn money from " + user.name + "'s account to bank";
                return response;
            }
        }
        changeMoneyResponse response = new changeMoneyResponse();
        response.statusCode = 425;
        response.message = "Your given information is wrong";
        return response;
    }
    public List<ProductPurchase> allSells() {
        return (List<ProductPurchase>) productPurchaseRepository.findAll();
    }
    public List<ProductPurchase> sellerSellList(int id) {
        return (List<ProductPurchase>) productPurchaseRepository.findById(id);
    }

}
