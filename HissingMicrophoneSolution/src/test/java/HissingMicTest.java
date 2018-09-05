/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
public class HissingMicTest {
    
    public HissingMicTest() {
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
     * Test of solve method, of class HissingMic.
     */
    @Test
    public void testSolve() throws Exception {
        System.out.println("solve");
        HissingMic micTest = new HissingMic();
        
        micTest.setInput("test");
        micTest.solve();
        assertEquals("no hiss", micTest.getOutput());
        
        micTest.setInput("hissing");
        micTest.solve();
        assertEquals("hiss", micTest.getOutput());
        
    }
    
}
