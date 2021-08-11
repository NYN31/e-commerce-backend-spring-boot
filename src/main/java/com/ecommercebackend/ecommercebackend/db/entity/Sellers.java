package com.ecommercebackend.ecommercebackend.db.entity;

import com.sun.xml.internal.ws.developer.UsesJAXBContext;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Sellers {
    @Id
    @Column(name = "id")
    public Integer id ;
    @Column(name = "name")
    public String name ;
    @Column(name = "email")
    public String email ;
    @Column(name = "password")
    public String password ;
    @Column(name ="company")
    public String company ;
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
