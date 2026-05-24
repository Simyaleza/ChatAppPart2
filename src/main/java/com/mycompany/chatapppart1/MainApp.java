/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapppart1;

import java.util.Random;
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
       
        // decision variables
        String response;
        int loginFail = 1;
        boolean running = true;
        
        // --------------- REGISTERATION SECTION ----------------
        System.out.println("==== User registration ============");
        do {
            System.out.print("Please enter username");
            String username = input.nextLine();

            System.out.print("Enter a password: ");
            String password = input.nextLine();

            System.out.print("Enter your South African phone number(+27...): ");
            String phone = input.nextLine();


            //Registering user
            response = login.registerUser(username, password, phone);

            // Showing the output of registering
            System.out.println(response);
        } while (response != "User registered successfully.");
        
        //-------------LOGIN SECTION ------------------
        System.out.println("\n========USER LOGIN========");
        
        while(loginFail <= 3){
        
            System.out.print("Enter your username");
            String loginUsername = input.nextLine();

            System.out.print("Enter your password");
            String loginPassword = input.nextLine();

            // checking if user exists
            boolean loggedIn = login.loginUser(loginUsername, loginPassword);
            
            if(loggedIn){
                String message = login.LoginStatus(loggedIn);
                System.out.println(message);
                loginFail = 3;
                System.out.println("=====================================");
                System.out.println("Welcome to ChatApp.");
                System.out.println("=====================================");
            }else{
                String message = login.LoginStatus(loggedIn);
                System.out.println(message);
                System.out.println("Attempts have done is "+ loginFail + ", if lands on 3 will be kicked!");
                loginFail++;
            }
            // telling the user the outcome
            
            
        }
        
        if (loginFail == 3){
            System.exit(0);
        }
        
        // ===================== Main App of program user experience ==========================
        
        while (running){
            System.out.println("1) Send Messages");
            System.out.println("2) Show recently sent messages");
            System.out.println("3) Quit");
            
            int choice = 0;
            choice = input.nextInt();
            
            switch (choice){
                case 1 : // when a user wants to send a message
                    System.out.println("How many messages would you like to send?");
                    int numMessages = 0;
                    
                    // used for the user to enter as much the user has requested.
                    for (int i =0; i< numMessages ; i++){
                       int MessageNum = i + 1;
                       System.out.println("---- Message " + MessageNum + " ----");
                       
                       Random random = new Random();
                       long number = 1000000000L + (long)(random.nextDouble() * 9000000000L);
                       String idString = String.valueOf(number);
                       
                       // ==================  User message enetered ==================
                       System.out.println("Enter your message:");
                       String Text = input.nextLine();
                       
                       if (Text.length() > 250){
                           int over = Text.length() - 250;
                           System.out.println("Your message is over " + over + "letters long, please reduce size next time");
                       }else{
                           System.out.println("message successfully captured");
                       }
                       
                    }
                    
                    break;
                case 2:
                    System.out.println("Feature coming soon, choose another function");
                    break;
                case 3:
                    running = false;
                    break;
                default:
                    System.out.println("Input invalid, please choose the correct option.");
            }
 
        }
        
    }
}
