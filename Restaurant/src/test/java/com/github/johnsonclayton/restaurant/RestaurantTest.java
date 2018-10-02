/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.johnsonclayton.restaurant;

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
public class RestaurantTest {
    
    public RestaurantTest() {
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
    public void testAddCustomers() {
        MenuItem fish_sandwhich = new MenuItem("fish sandwhich");
        fish_sandwhich.addToIngredients("fish");
        fish_sandwhich.addToIngredients("bread");
        
        MenuItems menu_items = new MenuItems();
        menu_items.add(fish_sandwhich);
        
        Ingredient fish = new Ingredient("fish", 50);
        Ingredient bread = new Ingredient("bread", 10);
        Ingredients ingredients = new Ingredients();
        ingredients.add(fish);
        ingredients.add(bread);
        
        Restaurant restaurant = new Restaurant(menu_items, 10, ingredients);
        
        Customer alice = new Customer("alice");
        Customer bob = new Customer("bob");
        
        restaurant.addCustomer(alice);
        restaurant.addCustomer(bob);
        
        assertEquals(restaurant.getCustomerCount(), 2);
        assertEquals(restaurant.getCustomerWaitingCount(), 0);
        
        restaurant = new Restaurant(menu_items, 1, ingredients);
        
        restaurant.addCustomer(alice);
        restaurant.addCustomer(bob);
        
        assertEquals(restaurant.getCustomerCount(), 1);
        assertEquals(restaurant.getCustomerWaitingCount(), 1);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testRemoveCustomers() {
        MenuItem fish_sandwhich = new MenuItem("fish sandwhich");
        fish_sandwhich.addToIngredients("fish");
        fish_sandwhich.addToIngredients("bread");
        
        MenuItems menu_items = new MenuItems();
        menu_items.add(fish_sandwhich);
        
        Ingredient fish = new Ingredient("fish", 50);
        Ingredient bread = new Ingredient("bread", 10);
        Ingredients ingredients = new Ingredients();
        ingredients.add(fish);
        ingredients.add(bread);
        
        Restaurant restaurant = new Restaurant(menu_items, 2, ingredients);
        
        Customer alice = new Customer("alice");
        Customer bob = new Customer("bob");
        Customer chris = new Customer("chris");
        
        restaurant.addCustomer(alice);
        restaurant.addCustomer(bob);
        restaurant.addCustomer(chris);
        
        assertEquals(restaurant.getCustomerCount(), 2);
        assertEquals(restaurant.getCustomerWaitingCount(), 1);
        assertEquals(restaurant.getAvailableTables(), 0);
        
        restaurant.removeCustomer(alice);
        
        assertEquals(restaurant.getCustomerCount(), 2);
        assertEquals(restaurant.getCustomerWaitingCount(), 0);
        assertEquals(restaurant.getAvailableTables(), 0);
        
        restaurant.removeCustomer(bob);
        
        assertEquals(restaurant.getCustomerCount(), 1);
        assertEquals(restaurant.getCustomerWaitingCount(), 0);
        assertEquals(restaurant.getAvailableTables(), 1);

        
        restaurant.removeCustomer(chris);
        
        assertEquals(restaurant.getCustomerCount(), 0);
        assertEquals(restaurant.getCustomerWaitingCount(), 0);
        assertEquals(restaurant.getAvailableTables(), 2);

        restaurant.removeCustomer(chris);
        
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testOrdering() {
        MenuItem fish_sandwhich = new MenuItem("fish sandwhich");
        fish_sandwhich.addToIngredients("fish");
        fish_sandwhich.addToIngredients("bread");
        
        MenuItems menu_items = new MenuItems();
        menu_items.add(fish_sandwhich);
        
        Ingredient fish = new Ingredient("fish", 1);
        Ingredient bread = new Ingredient("bread", 2);
        Ingredients ingredients = new Ingredients();
        ingredients.add(fish);
        ingredients.add(bread);
        
        Restaurant restaurant = new Restaurant(menu_items, 2, ingredients);
        
        Customer alice = new Customer("alice");
        Customer bob = new Customer("bob");
        
        restaurant.addCustomer(alice);
        restaurant.addCustomer(bob);
        
        alice.order(fish_sandwhich);
        bob.order(fish_sandwhich);
        
    }
}
