/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainApp.com.github.johnsonclayton.sheetmusicapp;

import java.util.ArrayList;

/**
 *
 * @author clayt
 */
public class Bar {
    private int length;
    private ArrayList<Note> notes;
    private int measureCount;
    
    void Bar() {
        measureCount = 0;
        length = 0;
        notes = new ArrayList();
    }

    public void makeMeasure(int measure, ArrayList<Note> notesForMeasure)
    {
        notes = notesForMeasure;
    }
    
    public ArrayList<Note> getNotes() {
        return notes;
    }
    
    
}
