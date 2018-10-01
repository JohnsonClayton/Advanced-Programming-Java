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
public class ItemsTest {
    
    public ItemsTest() {
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

    @Test
    public void testSomeMethod() {
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hasItemByName method, of class Items.
     */
    @Test
    public void testHasItemByName() {
        System.out.println("hasItemByName");
        String item_name = "";
        Items instance = new Items();
        boolean expResult = false;
        boolean result = instance.hasItemByName(item_name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of totalInCents method, of class Items.
     */
    @Test
    public void testTotalInCents() {
        System.out.println("totalInCents");
        Items instance = new Items();
        int expResult = 0;
        int result = instance.totalInCents();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
