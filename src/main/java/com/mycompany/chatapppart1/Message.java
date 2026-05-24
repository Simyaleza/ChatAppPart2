/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapppart1;

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
    
    
    // constructor
    public Message(String msgID, int msgNum, String reciver, String Text ){
        messageID = msgID;
        messageNumber = msgNum;
        recipient = reciver;
        messageText = Text;
    }
    
    // ------- checking if ID is less than 11 characters
    public boolean CheckMessageID(){
        return messageID.length() <= 10;
    }
    
    
    // --------- validating phone number
    public String  checkRecipientCell(){
        if (recipient.startsWith("+27") && recipient.length() <= 12){
            return "Cell phone number successfully captured.";
        }else{
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
    }
    
    // ---------- hashes the message
    public String createMessageHash(){
        // getting the 1st 2 char of the ID 
       String idBegining = messageID.substring(0,2);
       
       // SPLITTING THE TEXT by space
       String[] words = messageText.split(" ");
       
       // getting the 1st s& last space found
       String fWord = words[0];
       String lWord = words[words.length - 1];
       
       // formating the hash
       String hash = idBegining + ":" + messageNumber + ":"+ fWord + lWord;
       
       return hash.toUpperCase();
       
    }
    
    // ask the user to send, delete or store the message
    public String sentMessage(){
       System.out.println("What would you like to do with this message?");
       System.out.println("1) Send Message");
       System.out.println("2) Disregard Message");
       System.out.println("3) Store Message to send later");
       
       int option = 0; // read from scanner -- logic goes here
       
       switch (option) {
            case 1: 
                return "Message successfully sent.";
            case 2: 
                return "Press 0 to delete the message.";
            case 3:
                //storeMessage(); // call your JSON method
                return "Message successfully stored.";
            default:
                return "Please choose from oprions presented."; // handle unexpected input -- logic goes here
 } 
    }
    
    // displays every message sent 
   // public String printMessages()
    
    // returns the count of messages sent 
   // public int returnTotalMessages()
    
    //private void storeMessage()}
    
}
