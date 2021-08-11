package com.ecommercebackend.ecommercebackend.entities;

public class SellerRegDetails {
    private int Id;
    private String Name ;
    private String Email;
    private String Password;
    private String CompanyName;
    private String Address;
    private String LogoImagePath = "" ;
    private double Money ;
    private String BankName ;
    private String AccountNum ;

    public SellerRegDetails(int id, String name, String email, String password,
                            String companyName, String address, double money, String b_name, String a_num) {
        Id = id ;
        Name = name ;
        Email = email;
        Password = password;
        CompanyName = companyName ;
        Address = address ;
        LogoImagePath = "" ;
        Money = money ;
        BankName = b_name ;
        AccountNum = a_num ;
    }

    public int getId() { return Id; }
    public String getName() { return Name; }
    public String getEmail() { return Email; }
    public String getPassword() { return Password; }
    public String getCompanyName() { return CompanyName; }
    public String getAddress() { return Address; }
    public String getLogoImagePath() { return LogoImagePath; }
    public double getMoney() { return Money; }
    public String getBankName() { return BankName; }
    public String getAccountNumber() { return AccountNum; }
}
