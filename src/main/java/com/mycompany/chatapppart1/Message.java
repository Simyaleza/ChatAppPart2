/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapppart1;
import java.util.Scanner;

import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONObject;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author snopu
 */
public class Message {
    
    // attributes for the message class
    private String messageID; // Used for every message to uniquely identify
    private int messageNumber; // current msg we on 
    private String recipient; // To know who is reciving the message
    private String messageText; // The content of the msg
    private String messageHash; // Hashing the message
    private static int counter = 0;
    
    // arrays of messages
    private static List<String> sentMessages = new ArrayList<>(); 
    private static List<String> disregardedMessages = new ArrayList<>(); 
    private static List<String> storedMessages = new ArrayList<>(); 
    private static List<String> messageHashes = new ArrayList<>(); 
    private static List<String> messageIds = new ArrayList<>(); 
    
    Scanner input = new Scanner(System.in);
    
    // constructor
    public String messageConstruct( int msgNum, String Text ){
        
        if(Text.length() > 250){
            int over = Text.length() - 250;
            return "Your message is over " + over + "letters long, please reduce size next time";
        }else{
            this.messageNumber = msgNum;
            this.messageText = Text;
            return "message successfully captured" ;
            
        }
        
    }    
        
    
    
    // ------- checking if ID is less than 11 characters
    public boolean CheckMessageID(String msgID){
        if (msgID.length() <= 10){
           this.messageID = msgID;
           return true; 
        }else{
            return false;
        }
        
    }

    // --------- validating phone number
    public String  checkRecipientCell(String reciever){
        if (reciever.startsWith("+27") && reciever.length() <= 12){
            this.recipient = reciever;
            return "Cell phone number successfully captured.";
        }else{
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
    }
    
    // ---------- hashes the message
    public String createMessageHash(){
        // getting the 1st 2 char of the ID 
       String idBegining = this.messageID.substring(0,2);
       
       // SPLITTING THE TEXT by space
       String[] words = this.messageText.split(" ");
       
       // getting the 1st s& last space found
       String fWord = words[0];
       String lWord = words[words.length - 1];
       
       // formating the hash
       String hash = idBegining + ":" + this.messageNumber + ":"+ fWord + lWord;
       
       return hash.toUpperCase();
       
    }
    
    // ask the user to send, delete or store the message
    public String sentMessage(){
       System.out.println("What would you like to do with this message?");
       System.out.println("1) Send Message");
       System.out.println("2) Disregard Message");
       System.out.println("3) Store Message to send later");
       
       int option = input.nextInt(); // read from scanner -- logic goes here
       
       switch (option) {
            case 1: 
                counter++;
                storeMessage();
                return "Message successfully sent.";
            case 2:
                return "Message shall be deleted!";
            case 3:
                storeMessage(); // call your JSON method
                return "Message successfully stored.";
            default:
                return "Please choose from oprions presented."; // handle unexpected input -- logic goes here
 } 
    }
    
    // displays every message sent 
    public static List<String> printMessages(){

    try{

        return Files.readAllLines(Paths.get("messages.json"));

    } catch(IOException e){

        return null;
    }
}
    
    // returns the count of messages sent 
    public int returnTotalMessages(){
        return counter;
    }
    
    public void storeMessage(){
        JSONObject obj = new JSONObject();

        obj.put("messageID", this.messageID);
        obj.put("recipient", this.recipient);
        obj.put("message", this.messageText);

        try(FileWriter fw = new FileWriter("messages.json", true)){

            fw.write(obj.toString());
            fw.write("\n");

            System.out.println("Message stored.");

        } catch(IOException e){

            System.out.println("Error storing message.");
        }
    }
    
    
}
