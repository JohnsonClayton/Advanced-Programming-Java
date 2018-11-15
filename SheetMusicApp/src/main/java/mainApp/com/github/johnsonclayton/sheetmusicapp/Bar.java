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
    private ArrayList<Measure> measures;
    
    Bar() {
        measures = new ArrayList<>();
    }
    
    void addMeasure() {
        measures.add(new Measure());
    }
    
    void addNoteAtHitBox(Rectangle rect) {
        if(rect.measure_id < measures.size()) {
            measures.get(rect.measure_id).addNote(rect);
        }
    }
    
    public ArrayList<Measure> getMeasures() {
        return measures;
    }
    
    public void reset() {
        measures.clear();
        if(measures.isEmpty()) {
            System.out.println("Bar cleared");
        }
    }
    
    /*private int length;
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
    }*/

    

    

    
    
    
}
