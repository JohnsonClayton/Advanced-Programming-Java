/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.johnsonclayton.sqlite;

import java.sql.ResultSet;
import java.util.HashMap;
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
public class AppTest {
    
    @Test
    public void testInsert() {
        App instance = new App();
        instance.createNewDatabase();
        instance.createNewTable();
        
        instance.insert("Fork", 10);
        instance.insert("Spoon", 15);
        instance.insert("Chopstick", 6);
        instance.insert("Plate", 1);
        
        assertEquals(1, instance.getCountInTable("Plate"));
        assertEquals(10, instance.getCountInTable("Fork"));
        assertEquals(15, instance.getCountInTable("Spoon"));
        assertEquals(6, instance.getCountInTable("Chopstick"));
    }

    /**
     * Test of delete method, of class App.
     */
    @Test
    public void testDelete() {
        App instance = new App();
        instance.createNewDatabase();
        instance.createNewTable();
        
        instance.insert("Fork", 10);
        instance.insert("Spoon", 15);
        instance.insert("Chopstick", 6);
        instance.insert("Plate", 1);
        
        assertEquals(1, instance.getCountInTable("Plate"));
        assertEquals(10, instance.getCountInTable("Fork"));
        assertEquals(15, instance.getCountInTable("Spoon"));
        assertEquals(6, instance.getCountInTable("Chopstick"));
        
        instance.delete("Fork");
        instance.delete("Spoon");
        
        assertEquals(1, instance.getCountInTable("Plate"));
        assertEquals(0, instance.getCountInTable("Fork"));
        assertEquals(0, instance.getCountInTable("Spoon"));
        assertEquals(6, instance.getCountInTable("Chopstick"));        
    }
}
