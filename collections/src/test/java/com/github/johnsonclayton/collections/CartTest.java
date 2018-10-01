/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.johnsonclayton.collections;

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
public class CartTest {
    
    public CartTest() {
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
     * Test of addItem method, of class Cart.
     */
    @Test
    public void testAddItem() {
        System.out.println("addItem");
        String item_name = "";
        Cart instance = null;
        instance.addItem(item_name);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkout method, of class Cart.
     */
    @Test
    public void testCheckout() {
        System.out.println("checkout");
        Cart instance = null;
        instance.checkout();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hasItemByName method, of class Cart.
     */
    @Test
    public void testHasItemByName() {
        System.out.println("hasItemByName");
        String item_name = "";
        Cart instance = null;
        boolean expResult = false;
        boolean result = instance.hasItemByName(item_name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
