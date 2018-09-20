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
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author clayt
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({com.github.johnsonclayton.collections.ItemsTest.class, com.github.johnsonclayton.collections.ItemTest.class, com.github.johnsonclayton.collections.CustomersTest.class, com.github.johnsonclayton.collections.CollectionsAppTest.class, com.github.johnsonclayton.collections.RegistersTest.class, com.github.johnsonclayton.collections.CustomerTest.class, com.github.johnsonclayton.collections.MarketTest.class})
public class CollectionsSuite {

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
