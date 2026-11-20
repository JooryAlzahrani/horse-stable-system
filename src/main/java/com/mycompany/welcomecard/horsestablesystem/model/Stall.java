/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.welcomecard.horsestablesystem.model;

/**
 *
 * @author Joory 
 */
public class Stall {
    
    private int stallID;
    private String stallNumber;
    private int capacity;
    private String location;

    // Constructor
    public Stall(int stallID, String stallNumber,
                 int capacity, String location) {
        this.stallID = stallID;
        this.stallNumber = stallNumber;
        this.capacity = capacity;
        this.location = location;
    }

    // Getters
    public int getStallID() {
        return stallID;
    }

    public String getStallNumber() {
        return stallNumber;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getLocation() {
        return location;
    }

    // Setters
    public void setStallID(int stallID) {
        this.stallID = stallID;
    }

    public void setStallNumber(String stallNumber) {
        this.stallNumber = stallNumber;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
