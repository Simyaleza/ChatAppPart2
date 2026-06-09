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

import java.io.BufferedReader;
import java.io.FileReader;

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
    private static List<String> recipientList = new ArrayList<>();
    
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
       String hash = idBegining + ":" + this.messageNumber + ":"+ fWord +":"+ lWord;
       
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
                sentMessages.add(this.messageText);
                messageHashes.add(this.messageHash);
                messageIds.add(this.messageID);
                recipientList.add(this.recipient);
                return "Message successfully sent.";
            case 2:
                disregardedMessages.add(this.messageText);
                return "Message shall be deleted!";
            case 3:
                storeMessage(); // call your JSON method
                messageHashes.add(this.messageHash);
                messageIds.add(this.messageID);
                recipientList.add(this.recipient);
                return "Message successfully stored.";
            default:
                return "Please choose from oprions presented."; // handle unexpected input -- logic goes here
 } 
    }
    
    // displays every message sent 
    public static String printMessages(){
        
        if (sentMessages.size() == 0){
            return "No messages have been sent";
        }
        
        StringBuilder report = new StringBuilder();
        report.append("=== Message Report ====\n");
        for (int i = 0; i < sentMessages.size(); i++){
            report.append(messageHashes.get(i)+ " ");
            report.append(recipientList.get(i)+ " ");
            report.append(sentMessages.get(i));
            report.append("\n");
            
        }
        return report.toString();
   
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
    

    
    // ==================== part 3 ==================================
    
    // used to display all messages
    public String displayLongestMessage(){
        String longest = storedMessages.get(0);
        for (int i = 0; i < storedMessages.size();i++){
            if (storedMessages.get(i).length() > longest.length()) {
               longest = storedMessages.get(i);
            }
        }

        return longest;
    }
    
    
    // searching using the ID for the message
    public String searchByMessageID(String id){
        for (int i = 0;i< messageIds.size();i++){
           if (messageIds.get(i).equals(id)){
               String outputMsg = sentMessages.get(i);
               return outputMsg;
           } 
        }
        return "Message not found";
    }
    
    
    // search messages by recipient
    public String searchByRecipient(String recipient){
        StringBuilder results = new StringBuilder();
        // searching through stored messages
        for (int i = 0; i < recipientList.size();i++){
            if (recipientList.get(i).equals(recipient)){
                results.append(storedMessages.get(i));
                results.append("\n");
            }
        }
        
        if (results.length() == 0){
            return "No messages found.";
        }
        
        return results.toString();
    }
    
    // delete messageHash
    public String deletebyHash(String hash){
       for (int i = 0; i < messageHashes.size();i++){
           if(messageHashes.get(i).equals(hash)){
               // getting the deleted message
               String deletedMessage = storedMessages.get(i);
               
               // deleting any iteration of message
               messageHashes.remove(i);
               messageIds.remove(i);
               recipientList.remove(i);
               storedMessages.remove(i);
               
               // returning the message that was deleted 
               return "Message: " + deletedMessage + " successfully deleted.";
               
               
           }
       }
       
       return "Hash not found.";
    }
    
    
 
    public String displayStoredMessages() {

    StringBuilder display = new StringBuilder();

    try (BufferedReader reader = new BufferedReader(new FileReader("messages.json"))) {

        String line;

        while ((line = reader.readLine()) != null) {

            JSONObject obj = new JSONObject(line);

//            String messageID = obj.getString("messageID");
            String recipient = obj.getString("recipient");
            String message = obj.getString("message");

           
            display.append(" | Message: ")
                   .append(message)
                   .append(" | Recipient: ")
                   .append(recipient)
                   .append("\n");
        }

    } catch (IOException e) {

        System.out.println("messages.json file not found yet.");
        return "No stored messages found.";
    }

    return display.toString();
}
    
    
    public static void localStoredMessages(){
        try (BufferedReader reader = new BufferedReader(new FileReader("messages.json"))) {

            String line;

            while ((line = reader.readLine()) != null) {

                // convert line into JSON object
                JSONObject obj = new JSONObject(line);

                // extract values
                String messageID = obj.getString("messageID");
                String recipient = obj.getString("recipient");
                String message = obj.getString("message");

                // store message text
                storedMessages.add(message);

            }

        } catch (IOException e) {

            System.out.println("messages.json file not found yet.");
        }
    }
    
    
    
    // ================= Helper methods ================
    public static List<String> getSentMessages(){
        return sentMessages;
    }
    
    public static void clearArrays()    {
    sentMessages.clear();
    disregardedMessages.clear();
    storedMessages.clear();
    messageHashes.clear();
    messageIds.clear();
    recipientList.clear();
    }
    
    public static void addStoredMessage(String message){
    storedMessages.add(message);
    }
    

    public static void addRecipient(String recipient){
        recipientList.add(recipient);
    }
    
    public static void addMessageHash(String hash){
        messageHashes.add(hash);
    }

    public static void addMessageID(String id){
        messageIds.add(id);
    } 

}
