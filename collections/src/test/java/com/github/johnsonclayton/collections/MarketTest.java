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
public class MarketTest {
    
    public MarketTest() {
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
     * Test of addItem method, of class Market.
     */
    @Test
    public void testAddItem() {
        System.out.println("addItem");
        String item_name = "salt";
        int item_price = 100;
        Market instance = new Market();
        instance.addItem(item_name, item_price);
        assertTrue(instance.hasItemByName(item_name));
    }
    
    @Test
    public void testShopping() {
        Market market = new Market();
        market.addItem("salt", 100);
        market.addItem("cheese", 99);
        market.addItem("solar power strips", 79);
        
        Customer alice = new Customer("alice", 250);
        Cart cart = market.getCartFor(alice);
        cart.addItem("salt");
        cart.addItem("solar power strips");
        
        assertTrue(cart.hasItemByName("salt"));
        assertTrue(cart.hasItemByName("solar power strips"));
        
        cart.checkout();
        
        assertFalse(cart.hasItemByName("salt"));
        assertFalse(cart.hasItemByName("solar power strips"));
        
        assertFalse(market.hasItemByName("salt"));
        assertFalse(market.hasItemByName("solar power strips"));
        assertTrue(market.hasItemByName("cheese"));
        
        assertEquals(alice.moneyInCents, 250-100-79);
        assertTrue(alice.hasItemByName("salt"));
        assertTrue(alice.hasItemByName("solar power strips"));
    }

    /**
     * Test of printSales method, of class Market.
     */
    @Test
    public void testPrintSales() {
        System.out.println("printSales");
        int discountInPercent = 0;
        Market instance = new Market();
        instance.printSales(discountInPercent);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hasItemByName method, of class Market.
     */
    @Test
    public void testHasItemByName() {
        System.out.println("hasItemByName");
        String item_name = "";
        Market instance = new Market();
        boolean expResult = false;
        boolean result = instance.hasItemByName(item_name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCartFor method, of class Market.
     */
    @Test
    public void testGetCartFor() {
        System.out.println("getCartFor");
        Customer customer = null;
        Market instance = new Market();
        Cart expResult = null;
        Cart result = instance.getCartFor(customer);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
