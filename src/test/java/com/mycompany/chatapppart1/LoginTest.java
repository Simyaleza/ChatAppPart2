/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapppart1;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Student
 */
public class LoginTest {
   
    Login login = new Login();
    
    @Test// checking if the username validation
    public void usernameValidate(){
        boolean result = login.checkUsername("kyl_1");
        assertTrue(result);// returns true if entered correctly 
    }
    
    @Test// Ensuring false 
    public void usernameValidateFalse(){
        boolean result = login.checkUsername("kyl1!!!!!!!");
        assertFalse(result);// returns true if entered correctly 
    }
    
    //-----------------------------------------------------
    
    @Test// checking if the password is secure enough 
    public void passwordComplex(){
       boolean result = login.checkPasswordComplexity("Ch&&sec@ke99!");
        assertTrue(result);// should return true if complex  
    }
    
    @Test// checking if false the password is secure  
    public void passwordComplexFalse(){
       boolean result = login.checkPasswordComplexity("password");
        assertFalse(result);// should return true if complex  
    }
    //-----------------------------------------------------
    
    @Test // checking if phone is validated properly 
    public void phoneValidFormat(){
        boolean result = login.checkCellPhoneNumber("+2783896876");
        assertTrue(result);// should return true
    }
    @Test // checking if false phone is validated properly 
    public void phoneValidFormatFalse(){
        boolean result = login.checkCellPhoneNumber("0123456789");
        assertFalse(result);// should return true
    }
    
    // -----------------------------------------------------
    
    
    // Regsitering User
    @Test
    public void CheckRegsiter(){
        String result = login.registerUser("kyl_1", "Ch&&sec@ke99!", "+2783896876");
        System.out.println(result);
    }
    
    
    // testing if user is logged in 
    @Test
    public void LoginSuccess(){
        String Register = login.registerUser("kyl_1", "Ch&&sec@ke99!", "+2783896876");// first register student
        boolean result = login.loginUser("kyl_1", "Ch&&sec@ke99!");// then we log them in
        System.out.println(login.LoginStatus(result));// then see if user actually exists
    }
    
    
    
    
    
   
}
