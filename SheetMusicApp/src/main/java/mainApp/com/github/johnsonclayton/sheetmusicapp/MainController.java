/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainApp.com.github.johnsonclayton.sheetmusicapp;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

/**
 *
 * @author clayt
 */
class MainController extends JFrame{
    Bar bar;
    CustomPlayer player;
    
    //Panels inside MainWindow
    MainContentPanel mainPanel;
    PlayBackToolBar toolbar;
    
    MainController() {
        //Init
        setTitle("Clay's Sheet Music Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(500, 600));
        setExtendedState(JFrame.MAXIMIZED_BOTH);     
        
        //Create Music Playing Objects
        bar = new Bar();
        try {
            player = new CustomPlayer(bar);
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (LineUnavailableException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Create Panels
        mainPanel = new MainContentPanel();
        toolbar = new PlayBackToolBar();  
        
        mainPanel.setCommandListener(new CommandListener() {
            @Override
            public void commandRequested(int cmd) {}

            @Override
            public void commandRequested(int cmd, Rectangle rect) {
                //Handle the new note added
                //synchronized(this) {
                bar.addNoteAtHitBox(rect);
                //}
                System.out.println("Note received: " + rect.note_val);
            }
        });
        
        toolbar.setCommandListener(new CommandListener(bar, player) {
            @Override
            public void commandRequested(int cmd) {
                switch(cmd) {
                    case Util.PLAY_MUSIC:
                        System.out.println("Play"); 
                        if(player.isPaused) {
                            player.isPaused = false;
                        }
                        if(player.isStopped) {
                            {
                                try {
                                    player = new CustomPlayer(bar);
                                } catch (UnsupportedAudioFileException ex) {
                                    Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (LineUnavailableException ex) {
                                    Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (IOException ex) {
                                    Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        }
                        
                        player.play();
                        
                        break;
                    case Util.PAUSE_MUSIC:
                        System.out.println("Pause");
                        player.pause();
                        break;
                    case Util.STOP_MUSIC:
                        System.out.println("Stop");
                        player.isStopped = true;
                        break;
                    case Util.ADD_MEASURE:
                        mainPanel.addMeasure();
                        bar.addMeasure();
                        mainPanel.repaint();
                        break;
                    default:
                        System.out.println("Unknown command!");
                }
            }

            @Override
            public void commandRequested(int cmd, Rectangle rect) {}
        });
        
        bar.addMeasure();
        
        //Add panels
        add(toolbar, BorderLayout.NORTH);
        add(new JScrollPane(mainPanel), BorderLayout.CENTER);
        
        pack();
        setVisible(true);
    }
    
    public void testMe() {
        System.out.println("hello!!!!");
    }
}
