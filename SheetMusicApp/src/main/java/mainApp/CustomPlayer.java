/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainApp;

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
    public void CustomPlayer() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        currentIndex = -1;
        audioQueue = new ArrayList<String>();
        pausedTime = 0.00;
    }
    
    public void playSingle(String filename) throws UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException {
        //Play filename
        if (pausedTime == 0) {
            //using this article : https://www.codejava.net/coding/how-to-play-back-audio-in-java-with-examples        
            System.out.println("playSingle reached");
            File audioFile = new File(filename);
            stream = AudioSystem.getAudioInputStream(audioFile);

            format = stream.getFormat();
            info = new DataLine.Info(Clip.class, format);
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(stream);
            clip.start();
        }
        else {
            clip.setFramePosition((int)pausedTime);
            clip.start();
        }
        
        
    }
    
    public void playAudio() {
        //Play what's on the queue
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
