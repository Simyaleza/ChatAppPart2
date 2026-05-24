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
        
    }
    
    
    // --------- validating phone number
    public String  checkRecipientCell(){
        
    }
    
    // ---------- hashes the message
    public String createMessageHash(){
        
    }
    
    // ask the user to send, delete or store the message
    public String sentMessage(){
        
    }
    
    // displays every message sent 
    public String printMessages(){
        
    }
    
    // returns the count of messages sent 
    public int returnTotalMessages(){
        
    }
    
    private void stroreMessage(){
        
    }
    
}
