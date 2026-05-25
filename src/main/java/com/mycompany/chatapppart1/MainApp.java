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
        } while (response != "User registered successfully.");
        
        //-------------LOGIN SECTION ------------------
        System.out.println("\n========USER LOGIN========");
        
        while(loginFail <= 3){
        
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
        System.out.println(loginFail);
        if (loginFail >= 3){
            System.out.println("Too many attempts");
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
                    int numMessages = input.nextInt();
                    
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
                       
                        String msgOutput = message.sentMessage();
                        System.err.println(msgOutput);
                        System.out.println("");

                    }
                    
                    List<String> messages = Message.printMessages();
                    int msgCounter = message.returnTotalMessages();

                    System.out.println("                        ");
                    System.out.println("========================");
                    System.out.println("                        ");

                    System.out.println("Total messages sent: " + msgCounter);

                    if (messages != null){
                        for(String msg : messages){
                            
                            JSONObject obj = new JSONObject(msg);

                            System.out.println("Message: " + obj.getString("message"));
                            System.out.println("Recipient: " + obj.getString("recipient"));
                            System.out.println("MessageID: " + obj.getString("messageID"));

                            System.out.println();
                        }
                    }else{
                        System.out.println("No messages have been saved or sent, thxs for using the app");
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
        
        
        // once user quits
        System.out.println("                     ");
        System.out.println("======================");
        System.out.println("                       ");
        
        System.out.println("Thank you for using the app");
        
        try(FileWriter fw = new FileWriter("messages.json")){

            fw.write("");

        } catch(IOException e){

            System.out.println("Error clearing file.");
        }
        
    }
}
