/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.johnsonclayton.markettrading;

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
    
    @Test (expected = IllegalArgumentException.class)
    public void testRemoveStock() {
        Market instance = new Market();
        Stock test = new Stock();
        test.setPrice(5.00);
        test.setName("TEST");
        test.setNumberOfShares(100);
        
        instance.addStockToMarket(test);
        
        assertEquals(instance.sharesOf("TEST"), 100);
        assertEquals(5.00, instance.getPrice("TEST"), 0.01);
        
        instance.removeStockFromMarketByObject(test);
        
        instance.sharesOf("TEST");
        instance.getPrice("TEST");
    }
    
    @Test
    public void testBuy() {
        Customer alice = new Customer();
        alice.setMonies(57.00);
        
        Customer bob = new Customer();
        bob.setMonies(10.00);
        
        Customer charlie = new Customer();
        charlie.setMonies(17.00);
        
        Market instance = new Market();
        instance.customers.add(alice);
        instance.customers.add(bob);
        instance.customers.add(charlie);
        
        Stock test = new Stock();
        test.setPrice(14.00);
        test.setName("TEST");
        test.setNumberOfShares(5);
        
        instance.addStockToMarket(test);
        
        instance.stockPurchase(alice, "TEST", 4);
        instance.stockPurchase(bob, "TEST", 1);
        instance.stockPurchase(charlie, "TEST", 1);
        
        assertEquals(1.00, alice.moneyInDollars, 0.01);
        assertEquals(10.00, bob.moneyInDollars, 0);
        assertEquals(3.00, charlie.moneyInDollars, 0.01);
        
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testSell() {
        Customer alice = new Customer();
        alice.setMonies(57.00);
        
        Customer bob = new Customer();
        bob.setMonies(10.00);
        
        Customer charlie = new Customer();
        charlie.setMonies(17.00);
        
        Market instance = new Market();
        instance.customers.add(alice);
        instance.customers.add(bob);
        instance.customers.add(charlie);
        
        Stock test = new Stock();
        test.setPrice(14.00);
        test.setName("TEST");
        test.setNumberOfShares(5);
        
        instance.addStockToMarket(test);
        
        instance.stockPurchase(alice, "TEST", 4);
        instance.stockPurchase(bob, "TEST", 1);
        instance.stockPurchase(charlie, "TEST", 1);
        
        instance.stockSell(alice, "TEST", 3);
        instance.stockSell(bob, "TEST", 1);
        instance.stockSell(charlie, "TEST", 2);
        
        assertEquals(43.00, alice.moneyInDollars, 0.01);
        assertEquals(10, bob.moneyInDollars, 0);
        assertEquals(17.00, charlie.moneyInDollars, 0.01);   
    }   
}
