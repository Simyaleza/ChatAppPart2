/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapppart1;

import java.util.Scanner;

/**
 *
 * @author Student
 */
public class MainApp {
    public static void main(String[] args){
        
        // enables user to enter data
        Scanner input = new Scanner(System.in);
        
        // object of login being created
        Login login = new Login();
        
        
        
        // --------------- REGISTERATION SECTION ----------------
        System.out.println("==== User registration ============");
        
        System.out.print("Please enter username");
        String username = input.nextLine();
        
        System.out.print("Enter a password: ");
        String password = input.nextLine();
        
        System.out.print("Enter your South African phone number(+27...): ");
        String phone = input.nextLine();
        
        
        //Registering user
        String response = login.registerUser(username, password, phone);
        
        // Showing the output of registering
        System.out.println(response);
        
        
        //-------------LOGIN SECTION ------------------
        System.out.println("\n========USER LOGIN========");
        
        System.out.print("Enter your username");
        String loginUsername = input.nextLine();
        
        System.out.print("Enter your password");
        String loginPassword = input.nextLine();
        
        // checking if user exists
        boolean loggedIn = login.loginUser(loginUsername, loginPassword);
        
        // telling the user the outcome
        String message = login.LoginStatus(loggedIn);
        System.out.println(message);
    }
}
