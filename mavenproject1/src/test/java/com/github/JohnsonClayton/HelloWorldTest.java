/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.JohnsonClayton;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author clayt
 */
public class HelloWorldTest {
    
    public HelloWorldTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of setMessage method, of class HelloWorld.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void testSetMessage() {
        System.out.println("setMessage");
        String _message = null;
        HelloWorld instance = new HelloWorld();
        assertEquals("", instance.getMessage());
        instance.setMessage(_message);
        assertEquals(_message, instance.getMessage());
    }

    /**
     * Test of getMessage method, of class HelloWorld.
     */
    @Test
    public void testGetMessage() {
        System.out.println("getMessage");
        HelloWorld instance = new HelloWorld();
        String expResult = "";
        String result = instance.getMessage();
        assertEquals(expResult, result);
    }
    
}
