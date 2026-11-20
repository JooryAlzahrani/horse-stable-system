/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.welcomecard.horsestablesystem.model;

/**
 *
 * @author Joory 

 */
public class Horse {
    // attributes
    
    private int horseID;
    private String name;
    private String breed;
    private int age;
    private String gender;
    private int ownerID;
    private int stallID;
    
    
    // default constructor, auto-assigns values to null
    public Horse(){}
   
    // parameterized constructor
    public Horse(int horseID, String name, String breed, int age, String gender, int ownerID, int stallID){
        this.horseID = horseID;
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.gender = gender;
        this.ownerID = ownerID;
        this.stallID = stallID;
    }
    // getters and setters 
    
    public int getHorseID(){
        return this.horseID; 
    }
    
    public void setHorseID(int horseID){
        this.horseID = horseID;
    }
    
    public String getName(){
        return this.name;
    }
    
    public void setName(String name){
        this.name = name; 
    }
    
    public String getBreed(){
        return this.breed;
    }
    
    public void setBreed(String breed){
        this.breed = breed;
    }
    
    public int getAge(){
        return this.age;
    }
    
    public void setAge(int age){
        this.age = age;
    }
    
    public String getGender(){
        return this.gender;
    }
    
    public void setGender(String gender){
        this.gender = gender;
    }
    
    public int getOwnerID(){
        return this.ownerID;
    }
    
    public void setOwnerID(int ownerID){
        this.ownerID = ownerID;
    }
    
    public int getStallID(){
        return this.stallID;
    }
    
    public void setStallID(int stallID){
        this.stallID = stallID;
    }
}
