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
        beats.add(new ArrayList<>());
        beats.add(new ArrayList<>());
        beats.add(new ArrayList<>());
        beats.add(new ArrayList<>());

    }

    void addNote(Rectangle rect) {
        if(rect.beat >= 0 && rect.beat < 4) {
            boolean removed = false;
            
            for(Note note : beats.get(rect.beat)) { //Getting concurrent modification exception here but it works.
                if(rect.note_val == note.value) {
                    beats.get(rect.beat).remove(note);
                    removed = true;
                    //System.out.println("Removed " + rect.note_val + " from beat " + rect.beat + " on measure " + this);
                }
            }
            if(!removed) {
                beats.get(rect.beat).add(new Note(rect.note_val));
                //System.out.println("Added " + rect.note_val + " to beat " + rect.beat + " on measure " + this);
            }
        }
    }
    
    void addNoteAtBeat(int _beat, Note _note) {
        if(_beat >= 0 && _beat < 4) {
            beats.get(_beat).add(_note);
        }
    }
}
