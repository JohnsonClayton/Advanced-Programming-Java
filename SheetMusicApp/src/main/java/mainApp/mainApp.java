package mainApp.java;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFormat;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import mainApp.CustomPlayer;


public class mainApp extends JPanel implements ActionListener {
    protected JButton playButton, pauseButton, stopButton;
    protected CustomPlayer theDJ;
    
    public mainApp() {
        playButton = new JButton("Play");
        playButton.setActionCommand("play");
        playButton.addActionListener(this);
        playButton.setToolTipText("Start the music");
        
        pauseButton = new JButton("Pause");
        pauseButton.setActionCommand("pause");
        pauseButton.addActionListener(this);
        pauseButton.setToolTipText("Pause the music");
        
        stopButton = new JButton("Stop");
        stopButton.setActionCommand("stop");
        stopButton.addActionListener(this);
        stopButton.setToolTipText("Stop the music");
        
        add(playButton);
        add(pauseButton);
        add(stopButton);
        
        theDJ = new CustomPlayer();
    }
    
    public void actionPerformed(ActionEvent e) {
        if ("play".equals(e.getActionCommand())) {
            try {
                //Play music
                theDJ.playSingle("C:\\Users\\clayt\\projects\\csci310\\SheetMusicApp\\src\\main\\java\\mainApp\\xavier.wav");
            } catch (UnsupportedAudioFileException ex) {
                Logger.getLogger(mainApp.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(mainApp.class.getName()).log(Level.SEVERE, null, ex);
            } catch (LineUnavailableException ex) {
                Logger.getLogger(mainApp.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(mainApp.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Music playing");
        }
        else if ("pause".equals(e.getActionCommand())) {
            //Pause music
            theDJ.pauseAudio();
            System.out.println("Music paused");
        }
        else if ("stop".equals(e.getActionCommand())) {
            //Stop music
            theDJ.stopAudio();
            System.out.println("Music stopped");
        }
    }
    
    private static void createAndShowGUI() {
        JFrame frame = new JFrame("mainApp");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("mainApp");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        mainApp newContentPane = new mainApp();
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);
        
        frame.pack();
        frame.setVisible(true);
    }
    
    public static void main(String[] args) throws Exception {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}