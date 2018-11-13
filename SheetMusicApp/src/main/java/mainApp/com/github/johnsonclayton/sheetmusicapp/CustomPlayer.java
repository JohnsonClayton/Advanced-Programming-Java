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
import javax.swing.SwingWorker;

/**
 *
 * @author clayt
 */
public class CustomPlayer extends SwingWorker{
    private ArrayList<String> audioQueue;
    private int currentIndex;
    private double pausedTime;
    private AudioFormat format;
    private DataLine.Info info;
    private Clip clip;
    private AudioInputStream stream;
    private int interval;
    private Bar bar;
    
    boolean isPaused;
    boolean isStopped;
   
    
    CustomPlayer(Bar _bar) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        currentIndex = -1;
        audioQueue = new ArrayList<String>();
        pausedTime = 0.00;
        interval = 0;
        isPaused = false;
        isStopped = false;
        bar = _bar;
    }
    
     CustomPlayer() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        currentIndex = -1;
        audioQueue = new ArrayList<String>();
        pausedTime = 0.00;
        interval = 0;
    }
    
    public void playBar() {
        //Look at the link provided below and how I used the API in the past and figure it out
        
        /*
        *-----------------------
        *   General Algorithm
        *-----------------------
        *   Foreach measure in bar:
        *       Foreach ArrayList<Note> beat in measure.beats:
        *           if(beat.size > 0):
        *               Foreach Note note in beat:
        *                   Play (we want all notes on same beat to play at the same time)
        *               Wait until done playing
        *           else:
        *               Play rest
        *               Wait until done playing
        */
        
        //Note note = bar.getMeasures().get(0).beats.get(0).get(0);
        //System.out.println("playing note " + note.value + " (" + note.getNoteChar() + ") with file: " + Util.getFileNameForNote(note));
        try {
            for(Measure measure : bar.getMeasures()) {
                for(ArrayList<Note> beat : measure.beats) {
                    
                    if(isStopped) return;

                    
                    //Check to see if paused or stopped
                    while(isPaused) {
                        Thread.sleep(500);
                    }

                    
                    if(beat.size() > 0) {
                        for(Note note : beat) {
                            File audioFile = new File(Util.getFileNameForNote(note));
                            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

                            AudioFormat format = audioStream.getFormat();
                            DataLine.Info info = new DataLine.Info(Clip.class, format);

                            Clip audioClip = (Clip) AudioSystem.getLine(info);

                            audioClip.open(audioStream);
                            audioClip.start();
                        }
                        Thread.sleep(500);
                    }
                    else {
                        Thread.sleep(500);
                    }
                }
            }
        
        
        /*File audioFile = new File(Util.getFileNameForNote(note));
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
        
        AudioFormat format = audioStream.getFormat();
        DataLine.Info info = new DataLine.Info(Clip.class, format);
        
        Clip audioClip = (Clip) AudioSystem.getLine(info);
        
        audioClip.open(audioStream);
        audioClip.start();
        
        //End loop for beat
        
        Thread.sleep(1000);
        audioClip.close();
        audioStream.close();*/
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        this.isStopped = true;
        
        System.out.println("playBar reached");
    }
    
    public void playSingle(Rectangle rect) throws UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException {
        
        System.out.println("Playing note " + rect.note_val);
        
        File audioFile = new File(Util.getFileNameForRect(rect));
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

        AudioFormat format = audioStream.getFormat();
        DataLine.Info info = new DataLine.Info(Clip.class, format);

        Clip audioClip = (Clip) AudioSystem.getLine(info);

        audioClip.open(audioStream);
        audioClip.start();
        
        /*//Play filename
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
        */
        
    }
    
    public void playSingleNote(Note note) throws UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException {
        //Play filename
        //String filename = note.getFile();
        String filename = Util.getFileNameForNote(note);
        // needed note.setBusy();
        
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
        
        // needed note.setAvailable();
    }
    
    /*void playBar(Bar bar) throws UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException, InterruptedException, InterruptedException {
        //Play what is on the bar's queue (array)
        for(Note note : bar.getNotes()) {
            playSingleNote(note);
            Thread.sleep(note.getLength());
        }
    }*/
    
    public void pause() {
        //Pause audio + save time to pausedTime
        //clip.stop();
        //pausedTime = clip.getFramePosition();
        isPaused = true;
        
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

    @Override
    protected Object doInBackground() throws Exception {
        System.out.println("doInBackground reached");
        playBar();
        return true;
    }
    
    public void play() {
        System.out.println("play reached : " + this.getState());
        this.execute();
    }

   
}
