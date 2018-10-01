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
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author clayt
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({com.github.johnsonclayton.restaurant.CustomersTest.class, com.github.johnsonclayton.restaurant.RestaurantTest.class, com.github.johnsonclayton.restaurant.IngredientTest.class, com.github.johnsonclayton.restaurant.MenuItemTest.class, com.github.johnsonclayton.restaurant.MenuItemsTest.class, com.github.johnsonclayton.restaurant.IngredientsTest.class, com.github.johnsonclayton.restaurant.CustomerTest.class})
public class RestaurantSuite {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
}
