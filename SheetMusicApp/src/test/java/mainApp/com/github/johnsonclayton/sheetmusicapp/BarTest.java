/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainApp.com.github.johnsonclayton.sheetmusicapp;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
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
public class BarTest {
    
    public BarTest() {
    }

    /**
     * Test of addMeasure method, of class Bar.
     */
    @Test
    public void testAddMeasure() {
        Bar instance = new Bar();
        
        //Instance should not have notes in the beats
        assertEquals(0, instance.getMeasures().size());
        
        for(Measure measure : instance.getMeasures()) {
            for(ArrayList<Note> beat : measure.beats) {
                assertEquals(0, beat.size());
            }
        }
        
        instance.addMeasure();
        instance.addMeasure();
        instance.addMeasure();
        
        assertEquals(3, instance.getMeasures().size());
        
    }

    /**
     * Test of addNoteAtHitBox method, of class Bar.
     */
    @Test (expected = ConcurrentModificationException.class)
    public void testAddNoteAtHitBox() {
        Bar instance = new Bar();
        instance.addMeasure();
        
        int beat_size1 = instance.getMeasures().get(0).beats.get(0).size();
        assertEquals(0, beat_size1);
        int beat_size2 = instance.getMeasures().get(0).beats.get(1).size();
        assertEquals(0, beat_size2);
        int beat_size3 = instance.getMeasures().get(0).beats.get(2).size();
        assertEquals(0, beat_size3);
        int beat_size4 = instance.getMeasures().get(0).beats.get(3).size();
        assertEquals(0, beat_size4);
        
        Rectangle rect = new Rectangle(0, 0, 0, 0, Util.A_1, 0, 1);
        
        instance.addNoteAtHitBox(rect);
        
        beat_size1 = instance.getMeasures().get(0).beats.get(0).size();
        assertEquals(0, beat_size1);
        beat_size2 = instance.getMeasures().get(0).beats.get(1).size();
        assertEquals(1, beat_size2);
        beat_size3 = instance.getMeasures().get(0).beats.get(2).size();
        assertEquals(0, beat_size3);
        beat_size4 = instance.getMeasures().get(0).beats.get(3).size();
        assertEquals(0, beat_size4);
        
        rect = new Rectangle(0, 0, 0, 0, Util.A_1, 0, 1);
        instance.addNoteAtHitBox(rect);
        rect = new Rectangle(0, 0, 0, 0, Util.A_1, 0, 3);
        instance.addNoteAtHitBox(rect);
        
        beat_size1 = instance.getMeasures().get(0).beats.get(0).size();
        assertEquals(0, beat_size1);
        beat_size2 = instance.getMeasures().get(0).beats.get(1).size();
        assertEquals(2, beat_size2);
        beat_size3 = instance.getMeasures().get(0).beats.get(2).size();
        assertEquals(0, beat_size3);
        beat_size4 = instance.getMeasures().get(0).beats.get(3).size();
        assertEquals(1, beat_size4);
    }

    /**
     * Test of getMeasures method, of class Bar.
     */
    @Test
    public void testGetMeasures() {
        Bar instance = new Bar();
        
        assertEquals(0, instance.getMeasures().size());
        
        instance.addMeasure();
        instance.addMeasure();
        instance.addMeasure();       
        instance.addMeasure();
        instance.addMeasure();
        
        assertEquals(5, instance.getMeasures().size());
    }
    
}
