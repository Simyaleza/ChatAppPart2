/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapppart1;

import java.util.Random;
import java.util.Scanner;
import java.util.List;

import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONObject;

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
        Message message = new Message();
       
        // decision variables
        String response;
        int loginFail = 1;
        boolean running = true;
        
        // --------------- REGISTERATION SECTION ----------------
        System.out.println("==== User registration ============");
        do {
            System.out.print("Please enter username: ");
            String username = input.nextLine();

            System.out.print("Enter a password: ");
            String password = input.nextLine();

            System.out.print("Enter your South African phone number(+27...): ");
            String phone = input.nextLine();


            //Registering user
            response = login.registerUser(username, password, phone);

            // Showing the output of registering
            System.out.println(response);
        } while (!response.equals("User registered successfully."));
        
        //-------------LOGIN SECTION ------------------
        System.out.println("\n========USER LOGIN========");
        
        while(loginFail <= 4){
        
            System.out.print("Enter your username: ");
            String loginUsername = input.nextLine();

            System.out.print("Enter your password: ");
            String loginPassword = input.nextLine();

            // checking if user exists
            boolean loggedIn = login.loginUser(loginUsername, loginPassword);
            
            if(loggedIn){
                System.out.println(login.LoginStatus(loggedIn));
                System.out.println("=====================================");
                System.out.println("Welcome to ChatApp.");
                System.out.println("=====================================");
                break;
            }else{
                String messageStatus = login.LoginStatus(loggedIn);
                System.out.println(messageStatus);
                System.out.println("Attempts have done is "+ loginFail + ", if lands on 3 will be kicked!");
                loginFail++;
            }
            // telling the user the outcome
            
            
        }
        if (loginFail >= 4){
            System.out.println("Too many attempts");
            System.exit(0);
        }
        
        // ===================== Main App of program user experience ==========================
        message.localStoredMessages();
        while (running){
            System.out.println("1) Send Messages");
            System.out.println("2) Show recently sent messages");
            System.out.println("3) Quit");
            System.err.println("4) Stored Messages"); 
            
            int choice = 0;
            choice = input.nextInt();
            input.nextLine();
            
            switch (choice){
                case 1 : // when a user wants to send a message
                    System.out.println("How many messages would you like to send?");
                    int numMessages = input.nextInt();
                    input.nextLine();
                    
                    // used for the user to enter as much the user has requested.
                    for (int i =0; i< numMessages ; i++){
                       int MessageNum = i + 1;
                       System.out.println("---- Message " + MessageNum + " ----");
                       
                       // =============== ID ===========================
                       Random random = new Random();
                       long number = 1000000000L + (long)(random.nextDouble() * 9000000000L);
                       String idString = String.valueOf(number);
                       boolean correctID = message.CheckMessageID(idString);
                       System.out.println("Message ID: " +idString);
                       System.out.println();
                       
                       
                       // ============= Reciepient =============== 
                       String rcOutput;
                       String recipient;
                       do{
                        input.nextLine();
                        System.out.println("Enter recipient cell number (e.g. +2783896876): "); 
                        recipient = input.nextLine();
                        rcOutput = message.checkRecipientCell(recipient);
                          
                      } while (rcOutput != "Cell phone number successfully captured.");
                       
                       // ==================  User message enetered ==================
                       System.out.println("Enter your message:");
                       String Text = input.nextLine();
                       String msgText;
                       
                       do{
                           msgText = message.messageConstruct(MessageNum, Text);
                       }while (msgText != "message successfully captured");
                       
                       System.out.println(msgText);
                       String MessageHash = message.createMessageHash();
                       
                       System.out.println();
                       
                       
                       // =============== outputing Message ==============
                       System.out.println("MessageID: " + idString);
                       System.out.println("MessageHash: " + MessageHash);
                       System.out.println("Recipient: " + recipient);
                       System.out.println("Message: " + Text);
                       
                       System.out.println();
                       
                       
                       // Options of hat to do with message
                        System.out.println("What would you like to do with this message?");
                        System.out.println("1) Send Message");
                        System.out.println("2) Disregard Message");
                        System.out.println("3) Store Message to send later");

                        int option = input.nextInt(); // read from scanner -- logic goes here
                        input.nextLine();
                       
                        String msgOutput = message.sentMessage(option);
                        System.err.println(msgOutput);
                        System.out.println("");

                    }
                    
                    String report = message.printMessages();
                    System.out.println(report);
                    break;
                case 2:
                    System.out.println("Feature coming soon, choose another function");
                    break;
                case 3:
                    running = false;
                    break;
                    
                case 4:
                    boolean runMenu = true;
                    while(runMenu){
                    System.out.println("=========================");    
                    System.out.println("STORED MESSAGES MENU");    
                    System.out.println("=========================");    
                        
                        
                    System.out.println("a) Display all stored messages");
                    System.out.println("b) Display longest messages");// search via the length of the message
                    System.out.println("c) Search by message ID");
                    System.out.println("d) Search by recipient");
                    System.out.println("e) Delete by message hash");
                    System.out.println("f) Display full report");
                    System.out.println("g) Return to main menu");
                    
                    String optionInput = input.nextLine();
                    if (optionInput.isEmpty()) continue;
                    char option = optionInput.charAt(0);
                    
                    switch(option){
                        case 'a':
                            String display = message.displayStoredMessages();
                            System.out.println(display);
                            break;
                        case 'b':
                            String longest = message.displayLongestMessage();
                            System.out.println(longest);
                            break;
                        case 'c':
                            System.out.println("Please enter the ID, to search message");
                            String idSearch = input.nextLine();
                            String results = message.searchByMessageID(idSearch);
                            System.out.println(results);
                            break;
                        case 'd':
                            System.out.println("Enter recipient number to be searched");
                            String recipientSearch = input.nextLine();
                            System.out.println(message.searchByRecipient(recipientSearch));
                            break;
                        case 'e':
                            // delete by hash
                            System.out.println("Enter hash for message to be deleted!");
                            String hashInput = input.nextLine();
                            System.out.println(message.deletebyHash(hashInput));
                            break;
                        case 'f':
                            String msgOutput = message.printMessages();
                            if (msgOutput != null && !msgOutput.isEmpty()){
                                System.out.println(msgOutput);    
                            }else{
                                System.out.println("No messages have been sent.");
                            }
                            break;
                        case 'g':
                            runMenu = false;
                            break;
                        default:
                            System.out.println("Input invalid, please choose the correct option.");
                            
                            
                    }
                        
                    }
                    break;
                default:
                    System.out.println("Input invalid, please choose the correct option.");
            }
 
        }
        
        
        // once user quits
        System.out.println("                     ");
        System.out.println("======================");
        System.out.println("                       ");
        
        System.out.println("Thank you for using the app");
        

        
    }
}
