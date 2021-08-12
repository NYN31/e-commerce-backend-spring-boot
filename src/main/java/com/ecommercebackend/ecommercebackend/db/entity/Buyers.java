package com.ecommercebackend.ecommercebackend.db.entity;

import javax.persistence.*;

@Entity
public class Buyers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer id ;
    @Column(name = "name")
    public String name ;
    @Column(name = "email")
    public String email ;
    @Column(name = "password")
    public String password ;
    @Column(name = "address")
    public String address ;
    @Column(name = "pic_path")
    public String picPath ;
    @Column(name = "money")
    public Double money ;
    @Column(name = "bank_name")
    public String bankName ;
    @Column(name = "acc_num")
    public String accountNumber ;
}
