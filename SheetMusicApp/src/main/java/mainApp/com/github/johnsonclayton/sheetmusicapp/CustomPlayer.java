/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainApp.com.github.johnsonclayton.sheetmusicapp;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author clayt
 */
public class CustomPlayer {
    private ArrayList<String> audioQueue;
    private int currentIndex;
    private double pausedTime;
    private AudioFormat format;
    private DataLine.Info info;
    private Clip clip;
    private AudioInputStream stream;
    private int interval;
    public void CustomPlayer() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        currentIndex = -1;
        audioQueue = new ArrayList<String>();
        pausedTime = 0.00;
        interval = 0;
    }
    
    public void playSingle(String filename) throws UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException {
        //Play filename
        if (pausedTime == 0) {
            interval = 60; //FIX ME: The interval is messed up somewhere below
            if (interval == 0) {
                //using this article : https://www.codejava.net/coding/how-to-play-back-audio-in-java-with-examples        
                System.out.println("playSingle reached");
                File audioFile = new File(filename);
                stream = AudioSystem.getAudioInputStream(audioFile);

                format = stream.getFormat();
                info = new DataLine.Info(Clip.class, format);
                clip = (Clip) AudioSystem.getLine(info);
                clip.open(stream);
                clip.start();
            } else {
                //Play in accordance to the interval
                File audioFile = new File(filename);
                stream = AudioSystem.getAudioInputStream(audioFile);
                
                format = stream.getFormat();
                info = new DataLine.Info(Clip.class, format);
                clip = (Clip) AudioSystem.getLine(info);
                clip.open(stream);
                double start = System.currentTimeMillis();
                double currentTime = start;
                long millis = (long) (1000.0 * 60.0 / interval);
                System.out.println("1 beat every " + millis + " millis");
                while(currentTime - start < 5000) {
                    //play beat every interval
                    clip.setFramePosition(0);
                    clip.start();
                    Thread.sleep(millis);
                    currentTime = System.currentTimeMillis();
                    clip.stop();
                    //System.out.println("looped");
                }
            }
        }
        else {
            clip.setFramePosition((int)pausedTime);
            clip.start();
        }
        
        
    }
    
    public void playSingleNote(Note note) throws UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException {
        //Play filename
        String filename = note.getFile();
        note.setBusy();
        
        if (pausedTime == 0) {
            interval = 0; //FIX ME: The interval is messed up somewhere below
            if (interval == 0) {
                //using this article : https://www.codejava.net/coding/how-to-play-back-audio-in-java-with-examples        
                System.out.println("playSingle reached");
                File audioFile = new File(filename);
                stream = AudioSystem.getAudioInputStream(audioFile);

                format = stream.getFormat();
                info = new DataLine.Info(Clip.class, format);
                clip = (Clip) AudioSystem.getLine(info);
                clip.open(stream);
                clip.start();
            } else {
                //Play in accordance to the interval
                File audioFile = new File(filename);
                stream = AudioSystem.getAudioInputStream(audioFile);
                
                format = stream.getFormat();
                info = new DataLine.Info(Clip.class, format);
                clip = (Clip) AudioSystem.getLine(info);
                clip.open(stream);
                double start = System.currentTimeMillis();
                double currentTime = start;
                long millis = (long) (1000.0 * 60.0 / interval);
                System.out.println("1 beat every " + millis + " millis");
                while(currentTime - start < 5000) {
                    //play beat every interval
                    clip.setFramePosition(0);
                    clip.start();
                    Thread.sleep(millis);
                    currentTime = System.currentTimeMillis();
                    clip.stop();
                    //System.out.println("looped");
                }
            }
        }
        else {
            clip.setFramePosition((int)pausedTime);
            clip.start();
        }
        
        note.setAvailable();
    }
    
    void playBar(Bar bar) throws UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException, InterruptedException, InterruptedException {
        //Play what is on the bar's queue (array)
        for(Note note : bar.getNotes()) {
            playSingleNote(note);
            Thread.sleep(note.getLength());
        }
    }
    
    public void pauseAudio() {
        //Pause audio + save time to pausedTime
        clip.stop();
        pausedTime = clip.getFramePosition();
    }
    
    public void stopAudio() {
        //Stop audio + don't save information
        clip.close();
        pausedTime = 0;
    }
    
    public void addToQueue(String filename) {
        //Add filename to queue
    }
    
    public void addToQueueIndex(String filename, int index) {
        //Add filename to index of queue
    }
}
