package mainApp.com.github.johnsonclayton.sheetmusicapp;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioFormat;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JComponent;


public class mainApp extends JPanel implements ActionListener, MouseListener{
    protected JButton playButton, pauseButton, stopButton;
    protected CustomPlayer theDJ;
    protected Bar bar;
    
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
        
        bar = new Bar();
        
        //Testing Bar Functionality
        ArrayList<Note> testNotes = new ArrayList();
        Note note1 = new Note("440", 1, 1, 2000, "C:\\Users\\clayt\\projects\\csci310\\SheetMusicApp\\src\\main\\java\\mainApp\\com\\github\\johnsonclayton\\sheetmusicapp\\note1.wav");
        testNotes.add(0, note1);
        Note note2 = new Note("500", 1, 2, 2000, "C:\\Users\\clayt\\projects\\csci310\\SheetMusicApp\\src\\main\\java\\mainApp\\com\\github\\johnsonclayton\\sheetmusicapp\\note2.wav");
        testNotes.add(1, note2);
        Note note3 = new Note("540", 1, 3, 2000, "C:\\Users\\clayt\\projects\\csci310\\SheetMusicApp\\src\\main\\java\\mainApp\\com\\github\\johnsonclayton\\sheetmusicapp\\note3.wav");
        testNotes.add(2, note3);
        Note note4 = new Note("580", 1, 4, 2000, "C:\\Users\\clayt\\projects\\csci310\\SheetMusicApp\\src\\main\\java\\mainApp\\com\\github\\johnsonclayton\\sheetmusicapp\\note4.wav");
        testNotes.add(3, note4);
        bar.makeMeasure(1, testNotes);
        
        this.setBackground(Color.RED);
        
        addMouseListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
        if ("play".equals(e.getActionCommand())) {
            try {
                //Play music
                //theDJ.playSingle("C:\\Users\\clayt\\projects\\csci310\\SheetMusicApp\\src\\main\\java\\mainApp\\xavier.wav");
                //theDJ.playSingle("C:\\Users\\clayt\\projects\\csci310\\SheetMusicApp\\src\\main\\java\\mainApp\\com\\github\\johnsonclayton\\sheetmusicapp\\snap.wav");
                theDJ.playBar(bar);
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
    
    @Override
    public void mousePressed(MouseEvent e) {
        //System.out.println("Mouse clicked");
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
        //System.out.println("Mouse entered");
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        //System.out.println("Mouse exited");
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
        //System.out.println("Mouse released");
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        //System.out.println("Mouse clicked");
    }
}

class BackgroundImage extends JComponent{
    private Image image;
    
    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.drawImage(image, 0, 0, this);
    }
}