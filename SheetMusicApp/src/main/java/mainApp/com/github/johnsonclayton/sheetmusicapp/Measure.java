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
class Measure { 
    ArrayList<ArrayList<Note>> beats;
    Measure() {
        beats = new ArrayList<>();
    }

    void addNote(Rectangle rect) {
        if(rect.beat < 4) {
            boolean removed = false;
            for(Note note : beats.get(rect.beat)) {
                if(rect.note_val == note.value) {
                    beats.get(rect.beat).remove(note);
                    removed = true;
                }
            }
            if(!removed) {
                beats.get(rect.beat).add(new Note(rect.note_val));
            }
        }
    }
}
