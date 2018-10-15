/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.johnsonclayton.markettrading;

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
    
    @Test
    public void testAddingStock() {
        Market instance = new Market();
        Stock test = new Stock();
        test.setPrice(5.00);
        test.setName("TEST");
        test.setNumberOfShares(100);
        instance.addStockToMarket(test);
        assertEquals(instance.sharesOf("TEST"), 100);
        assertEquals(5.00, instance.getPrice("TEST"), 0.01);
    }
    
    @Test
    public void testRemoveStock() {
        
    }
    
    @Test
    public void testBuy() {
        
    }
    
    @Test
    public void testSell() {
        
    }
    
    
}
