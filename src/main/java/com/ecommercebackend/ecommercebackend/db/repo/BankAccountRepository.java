package com.ecommercebackend.ecommercebackend.db.repo;

import com.ecommercebackend.ecommercebackend.db.entity.BankAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.core.CrudMethods;

public interface BankAccountRepository extends CrudRepository<BankAccount, Integer> {

}
