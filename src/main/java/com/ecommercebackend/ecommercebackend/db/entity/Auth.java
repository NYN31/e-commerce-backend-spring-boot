package com.ecommercebackend.ecommercebackend.db.entity;

import javax.persistence.*;

@Entity
@Table(name = "auths")
public class Auth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
