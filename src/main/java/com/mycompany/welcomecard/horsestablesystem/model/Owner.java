/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.welcomecard.horsestablesystem.model;

/**
 *
 * @author Joory
 */
public class Owner {
   
    private int ownerID;
    private String name;
    private String phone;
    private String email;
    private String address;
    
    public Owner(){}
    
    public Owner(int ownerID, String name, String phone,
                 String email, String address) {
        this.ownerID = ownerID;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }


    public int getOwnerId() {
        return ownerID;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

   
    public void setOwnerID(int ownerID) {
        this.ownerID = ownerID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}


