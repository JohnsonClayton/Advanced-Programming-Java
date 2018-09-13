/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainApp.com.github.johnsonclayton.sheetmusicapp;

/**
 *
 * @author clayt
 */
public class Note {
    private String filename;
    private String notename;
    private int measure;
    private int beat;
    private int length; //in milliseconds
    private boolean isPlaying;
    
    public Note(String _note, int _measure, int _beat, int millis, String _file) {
        notename = _note;
        measure = _measure;
        beat = _beat;
        filename = _file;
        length = millis;
        isPlaying = false;
    }
    
    public int getLength() {
        return length;
    }
    
    public String getFile() {
        return filename;
    }
    
    public void setFile(String _file) {
        filename = _file;
    }
    
    public String getNoteName() {
        return notename;
    }
    
    public void setNoteName(String _note) {
        notename = _note;
    }
    
    public int getMeasureNum() {
        return measure;
    }
    
    public int getBeat() {
        return beat;
    }

    public void setBusy() {
        isPlaying = true;
    }

    public void setAvailable() {
        isPlaying = false;
    }

    public boolean isPlaying() {
        return isPlaying;
    }
    
}
