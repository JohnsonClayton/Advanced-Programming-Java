/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainApp.com.github.johnsonclayton.sheetmusicapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
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
        if(!_file.endsWith(".json")) {
            _file += ".json";
        }
        try {
            FileOutputStream stream = new FileOutputStream(_file);
            
            JsonWriter writer = Json.createWriter(stream);
            writer.writeObject(json_bar);
            writer.close();
            current_filename = _file;
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
    
    public void createJSONFromBar(Bar bar) {
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
    
     public Bar createBarFromJSON(String _file) throws FileNotFoundException {
        //Parse through json file and create Bar object
        Bar new_bar = new Bar();
        JsonReader reader = Json.createReader(new FileInputStream(_file));
        JsonObject json_input = reader.readObject();
        reader.close();
        boolean null_found = false;
        
        JsonObject json_measure;
        JsonArray json_array;
        String key;
        
        for(int i = 0; !null_found; i++) {
            System.out.println("Pulling up new measure");
            /*//For every measure object in json
            Measure measure = new Measure();
            json_measure = json_input.getJsonObject("measure_" + i);
            for(int j = 0; j < 4; j++) {
                //For every beat in json
                ArrayList<Note> beat = new ArrayList<>();
                for()
                
            }
            new_bar.addBuiltMeasureObject(measure);*/
            key = "measure_" + i;
            json_measure = json_input.getJsonObject(key);
            if(json_measure != null) {
                Measure measure = new Measure();
                for(int j = 0; j < 4; j++) {
                    json_array = json_measure.getJsonArray("beat_" + j);
                    if(json_array != null) {
                        for(int z = 0; z < json_array.size(); z++) {
                            //Beat j
                            Note note = new Note(((JsonNumber)json_array.get(z)).intValue());
                            measure.addNoteAtBeat(j, note);                           
                            System.out.println("Just added note object " + note.value + " at beat " + j);
                        }
                    }
                }
                new_bar.addBuiltMeasureObject(measure);
            } else {
                null_found = true;
            }
        }
        
        return new_bar;
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
