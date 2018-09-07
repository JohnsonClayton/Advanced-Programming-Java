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
public class CountingStarsSolutionTest {
    
    public CountingStarsSolutionTest() {
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
     * Test of main method, of class CountingStarsSolution.
     */
    @Test
    public void testMain() throws Exception {
        System.out.println("main");
        String[] args = null;
        CountingStarsSolution.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    @Test
    public void testCase1() throws Exception {
        CountingStarsSolution instance = new CountingStarsSolution();
        instance.rows = 10;
        instance.columns = 20;
        instance.picture = new boolean[10][20];
        
        int row = 0;
        instance.picture[row++] = instance.parseRow("#################---");
        instance.picture[row++] = instance.parseRow("##-###############--");
        instance.picture[row++] = instance.parseRow("#---################");
        instance.picture[row++] = instance.parseRow("##-#################");
        instance.picture[row++] = instance.parseRow("########---#########");
        
        instance.picture[row++] = instance.parseRow("#######-----########");
        instance.picture[row++] = instance.parseRow("########---#########");
        instance.picture[row++] = instance.parseRow("##################--");
        instance.picture[row++] = instance.parseRow("#################---");
        instance.picture[row++] = instance.parseRow("##################-#");
       
        instance.solve();
        assertEquals(4, instance.stars);
    }
    
    @Test
    public void testCase2() throws Exception {
        CountingStarsSolution instance = new CountingStarsSolution();
        instance.rows = 3;
        instance.columns = 10;
        instance.picture = new boolean[3][10];
        
        int row = 0;
        instance.picture[row++] = instance.parseRow("#-########");
        instance.picture[row++] = instance.parseRow("----------");
        instance.picture[row++] = instance.parseRow("#-########");

        instance.solve();
        assertEquals(1, instance.stars);
    }

    /**
     * Test of solve method, of class CountingStarsSolution.
     */
    @Test
    public void testSolve() {
        System.out.println("solve");
        CountingStarsSolution instance = new CountingStarsSolution();
        instance.solve();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of parseRow method, of class CountingStarsSolution.
     */
    @Test
    public void testParseRow() {
        CountingStarsSolution instance = new CountingStarsSolution();
        boolean[] result = instance.parseRow("#-########");
        boolean[] expect = new boolean[] {false, true, false, false, false, false, false, false, false, false};
        
        assertArrayEquals(expect, result);
    }
}
