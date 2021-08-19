package com.ecommercebackend.ecommercebackend.db.entity;

import javax.persistence.*;

@Entity
@Table(name = "bank_accounts")
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer id ;
    @Column(name = "user_id")
    public Integer userId;
    @Column(name = "bank_name")
    public String bankName;
    @Column(name = "bank_branch")
    public String bankBranch;
}
