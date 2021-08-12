package com.ecommercebackend.ecommercebackend.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Auth {
    @Id
    @Column(name = "id")
    public Integer id ;
    @Column(name = "email")
    public String email ;
    @Column(name = "password")
    public String password ;
    @Column(name = "type")
    public String type ;
    @Column(name = "status")
    public Integer status ;
}
