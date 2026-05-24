/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapppart1;

/**
 *
 * @author Student
 */
public class Login {
    String username;
    String password;
    String phoneNumber;
    
    // VALIDATION FEATURE ------------------------------------------------------------------------------------------------------
    
    // checking if username entered has an underscore & has 5 characters or less
    public boolean checkUsername(String username){
        return username.contains("_") && username.length() <= 5;
    }
    
    // Password validation, checks if meets correct standards, returns true if the case
    public boolean checkPasswordComplexity(String password){
        
        boolean hasCaptial = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;
        
        for(int i = 0; i < password.length(); i++){
            char c = password.charAt(i);
            
            if(Character.isUpperCase(c)){
                hasCaptial = true;
            } else if(Character.isDigit(c)){
                hasNumber = true;
            }else if(!Character.isLetterOrDigit(c)){
                hasSpecial = true;
            }
            
        }
        
        return password.length() >= 8 && hasCaptial && hasNumber && hasSpecial;
    }
    
    //Checks if phone number entered is correct
    public boolean checkCellPhoneNumber(String phone){
        return phone.startsWith("+27") && phone.length() <= 12;
    }
    
    
    
    // REGISTERING USER ------------------------------------------------------------------------------------------------------
    public String registerUser(String username, String password, String phoneNumber) { 
        // checking username entered
        if (!checkUsername(username)) { 
            return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length."; 
        }
        // checking how complex password is
        if (!checkPasswordComplexity(password)) { 
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character."; 
        } 
        
        // checking phone no. is correct format in the region
        if (!checkCellPhoneNumber(phoneNumber)) { 
            return "Cell phone number incorrectly formatted or does not contain international code."; 
        } 
        
        // giving the details to the class, belongs to the class
        this.username = username; 
        this.password = password; 
        this.phoneNumber = phoneNumber; 
        
        return "User registered successfully."; 
    } 
    
    
    // LOGIN FEATURE -----------------------------------------------------------------------------------------------
    
    // login user to system
    public boolean loginUser(String username, String password){
        if(this.username == null || this.password == null){
        return false;
    }

    return this.username.equals(username) && this.password.equals(password);
    }
    
    // status if user exists the system or not
    public String LoginStatus(boolean success){
        if(success){
            return "Welcome" + this.username + "it is great to see you again.";
        }else{
            return "Username or password incorrect, plz try again";
        }
    }
}
