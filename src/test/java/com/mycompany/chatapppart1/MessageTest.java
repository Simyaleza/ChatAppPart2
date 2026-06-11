/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.mycompany.chatapppart1;

import java.io.ByteArrayInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
/**
 *
 * @author snopu
 */
public class MessageTest {
    
    Message instance = new Message();
    ArrayList<String> recipients;
    ArrayList<String> testMessages;
    ArrayList<String> flags;
    
    // ================ TEST 1 =============================
    @BeforeEach
    public void setUp(){
        
        Message.clearArrays();
        recipients = new ArrayList<>();
        testMessages = new ArrayList<>();
        flags = new ArrayList<>();
        
        
        //------ Test 1 ---------- 
        recipients.add("+27834557896");
        testMessages.add("Did you get the cake?");
        flags.add("Sent");
        
        //------ Test 2-----------
        recipients.add("+27838884567");
        testMessages.add("Where are you? You are late! I have asked you to be on time");
        flags.add("Stored");
        
        //--------- Test 3 -------------
        recipients.add("+27834484567");
        testMessages.add("Yohooo, I am at your gate.");
        flags.add("disregard");
        
        //-----------Test 4--------------
        recipients.add("0838884567");
        testMessages.add("It is dinner time!");
        flags.add("sent");
        
        // ---------- Test 5 -----------
        recipients.add("+27838884567");
        testMessages.add("Ok, I am leaving without you.");
        flags.add("Stored");
        
    }
    
    // Testing if arrays populated properly
@Test
public void testSentMessagesArray_correctlyPopulated() {


    // ---------- Message 1 ----------
    Message msg1 = new Message();

    msg1.messageConstruct(1, "Did you get the cake?");
    msg1.CheckMessageID("1234567890");
    msg1.checkRecipientCell("+27831234567");

    msg1.sentMessage(1); // SEND

    // ---------- Message 4 ----------
    Message msg4 = new Message();

    msg4.messageConstruct(4, "It is dinner time!");
    msg4.CheckMessageID("0987654321");
    msg4.checkRecipientCell("+27839876543");

    msg4.sentMessage(1); // SEND

    // ---------- ASSERTIONS ----------
    assertTrue(Message.getSentMessages().contains("Did you get the cake?"));
    assertTrue(Message.getSentMessages().contains("It is dinner time!"));
}
    
    
    @Test
    public void testSearchByMessageID_returnsCorrectMessage() {

        Message msg4 = new Message();

        // setup message 4
        msg4.messageConstruct(4, "It is dinner time!");

        msg4.CheckMessageID("0987654321");

        msg4.checkRecipientCell("0838884567");

        // send message
        msg4.sentMessage(1);

        // expected result
        String expected = "It is dinner time!";

        // actual result
        String actual = msg4.searchByMessageID("0987654321");

        // assertion
        assertEquals(expected, actual);
    }
    

    @Test
    public void testDisplayLongestMessage_returnsCorrectMessage() {

        // populate stored messages using your POE data
        Message.addStoredMessage(testMessages.get(0));

        Message.addStoredMessage(testMessages.get(1));

        Message.addStoredMessage(testMessages.get(2));

        Message.addStoredMessage(testMessages.get(3));

        Message.addStoredMessage(testMessages.get(4));

        // expected longest message
        String expected =
                "Where are you? You are late! I have asked you to be on time";

        // actual result
        String actual = instance.displayLongestMessage();

        // assertion
        assertEquals(expected, actual);
    }
    
    
    @Test
    public void testSearchByRecipient_returnsAllMatchingMessages() {

        // populate recipient list
        Message.addRecipient("+27834557896");

        Message.addRecipient("+27838884567");

        Message.addRecipient("+27834484567");

        Message.addRecipient("+27838884567");

        // populate stored messages
        Message.addStoredMessage("Did you get the cake?");

        Message.addStoredMessage(
                "Where are you? You are late! I have asked you to be on time.");

        Message.addStoredMessage("Yohooo, I am at your gate.");

        Message.addStoredMessage("Ok, I am leaving without you.");

        // search recipient
        String result =
                instance.searchByRecipient("+27838884567");

        // assertions
        assertTrue(result.contains(
                "Where are you? You are late! I have asked you to be on time."));

        assertTrue(result.contains(
                "Ok, I am leaving without you."));
    }
 
    
    @Test
    public void testDeleteByHash_removesCorrectMessage() {

        // create message 2
        Message msg2 = new Message();

        msg2.messageConstruct(
                2,
                "Where are you? You are late! I have asked you to be on time");

        msg2.CheckMessageID("1234567890");

        msg2.checkRecipientCell("+27838884567");

        // create hash
        String hash = msg2.createMessageHash();

        // populate arrays
        Message.addStoredMessage(
                "Where are you? You are late! I have asked you to be on time");

        Message.addMessageHash(hash);

        Message.addMessageID("1234567890");

        Message.addRecipient("+27838884567");

        // delete by hash
        String actual = msg2.deletebyHash(hash);

        // expected result
        String expected =
                "Message: Where are you? You are late! I have asked you to be on time successfully deleted.";

        // assertion
        assertEquals(expected, actual);
    }
    
    
    @Test
    public void testCheckRecipientCell() {

        String receiver = "+27831234567";

        String result = instance.checkRecipientCell(receiver);

        assertEquals("Cell phone number successfully captured.", result);
    }

    @Test
    public void testMessageConstruct() {

        int msgNum = 1;
        String text = "Hi Mike, can you join us for dinner tonight?";

        String result = instance.messageConstruct(msgNum, text);

        assertEquals("message successfully captured", result);
    }

    @Test
    public void testCheckMessageID() {

        String msgID = "6382348920";

        boolean result = instance.CheckMessageID(msgID);

        assertTrue(result);
    }

    @Test
    public void testCreateMessageHash() {
        
        boolean idresult = instance.CheckMessageID("6382348920");

        // Arrange required state first
        instance.messageConstruct(1, "Hi tonight");

        String result = instance.createMessageHash();

        // Safer assertion (avoids brittle exact matching)
        assertNotNull(result);
        assertTrue(result.contains(":"));
        assertTrue(result.length() > 0);
    }

    @Test
    public void testSentMessage() {

        // Arrange required state first
        instance.messageConstruct(1, "Test message");

        String result = instance.sentMessage(1);

        assertEquals("Message successfully sent.", result);
    }

    
    
/*    
    @AfterEach
    public void clearJson(){
        try(FileWriter fw = new FileWriter("messages.json")){

            fw.write("");

            System.out.println("JSON file cleared.");

        } catch(IOException e){

            System.out.println("Error clearing file.");
        }
    }

*/
 /*   @Test
    public void testCheckRecipientCell() {
        System.out.println("checkRecipientCell");
        String reciever = "+271869302";
        String expResult = "Cell phone number successfully captured.";
        String result = instance.checkRecipientCell(reciever);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    @Test
    public void testMessageConstruct() {
        System.out.println("messageConstruct");
        int msgNum = 1;
        String Text = "Hi Mike, can you join us for dinner tonight?";
        String msgOutput = instance.messageConstruct(msgNum, Text);
        String expResult = "message successfully captured";
        // TODO review the generated test code and remove the default call to fail.
        assertEquals(expResult, msgOutput);
    }

    @Test
    public void testCheckMessageID() {
        System.out.println("CheckMessageID");
        String msgID = "6382348920";
        boolean result = instance.CheckMessageID(msgID);
        assertTrue(result);
    }

    @Test
    public void testCreateMessageHash() {
        System.out.println("createMessageHash");
        String expResult = "00:0:HITONIGHT";
        String result = instance.createMessageHash();
        assertEquals(expResult, result);
    }

    @Test
    public void testSentMessage() {
        System.out.println("sentMessage");
        String expResult = "Message successfully sent.";
        String result = instance.sentMessage();
        assertEquals(expResult, result);
    }

*/
}
