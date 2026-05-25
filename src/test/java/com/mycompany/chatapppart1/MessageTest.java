/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.mycompany.chatapppart1;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author snopu
 */
public class MessageTest {
    
    Message instance = new Message();
    
    // ================ TEST 1 =============================

    @Test
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


}
