/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainApp.com.github.johnsonclayton.sheetmusicapp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;

/**
 *
 * @author clayt
 */
public class CustomFileManager {
    private String current_filename;
    private JsonObject json_bar;
    
    CustomFileManager() {
        current_filename = null;
        json_bar = null;
    }
    
    public void writeToFile(String _file) {
        try {
            //How to make output stream from file desired to output to???
            FileOutputStream stream = new FileOutputStream("C:\\Users\\clayt\\projects\\csci310\\SheetMusicApp\\src\\main\\resources\\savedFile.json");
            
            JsonWriter writer = Json.createWriter(stream);
            writer.writeObject(json_bar);
            writer.close();
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        
        
    }
    
    public boolean currentFileHasName() {
        boolean isNull = false;
        if(current_filename == null) {
            isNull = true;
        }
        return isNull;
    }
    
    public void createJSON(Bar bar) {
        //Parse Bar Object to a JsonObject
        JsonObjectBuilder super_builder = Json.createObjectBuilder();
        
        ArrayList<Measure> measures = bar.getMeasures();
        
        for(Measure measure : measures) {
            JsonObjectBuilder beats = Json.createObjectBuilder();
            for(ArrayList<Note> beat : measure.beats) {
                JsonArrayBuilder notes = Json.createArrayBuilder();
                for(Note note : beat) {
                    notes.add(note.value);
                }
                beats.add("beat_" + measure.beats.indexOf(beat), notes);
            }
            super_builder.add("measure_" + measures.indexOf(measure), beats);
        }
        
        json_bar = super_builder.build();
    }
    
    public String getCurrentFileName() {
        return current_filename;
    }
    public void setCurrentFileName(String _filename) {
        current_filename = _filename;
    }
    
    public void reset() {
        current_filename = null;
        json_bar = null;
    }
}
