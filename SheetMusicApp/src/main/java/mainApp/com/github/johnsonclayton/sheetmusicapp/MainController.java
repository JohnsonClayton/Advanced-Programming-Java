/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainApp.com.github.johnsonclayton.sheetmusicapp;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.json.JsonObject;
import javax.json.*;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;


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
    
    JPopupMenu optionsMenu;
    JMenuBar menuBar;
    
    CustomFileManager file_manager;
    
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
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Create Panels
        mainPanel = new MainContentPanel();
        toolbar = new PlayBackToolBar();  
        
        mainPanel.setCommandListener(new CommandListener() {
            @Override
            public void commandRequested(int cmd) {
                switch(cmd) {
                    case Util.POP_UP_TRIGGERED:
                        System.out.println("pop up reached");
                        optionsMenu.show(mainPanel.mouseEvent.getComponent(), mainPanel.mouseEvent.getX(), mainPanel.mouseEvent.getY());
                        
                        System.out.println("pop up triggered");
                        break;
                    default:
                        break;
                }
            }

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
        
        createMenuBar();
        
        
        
        optionsMenu = new JPopupMenu();
        /*
        JMenuItem item1 = new JMenuItem("Sharp");
        JMenuItem item2 = new JMenuItem("Flat");
        JMenuItem item3 = new JMenuItem("Natural");*/
        
        JMenuItem playSingleNoteButton = new JMenuItem("Play Note");
        playSingleNoteButton.addActionListener((ActionEvent e) -> {
            //Find the selected note (should be gray)
            Rectangle rect = mainPanel.getSelectedNote();
            if(rect != null) {
                try {
                    //Play rect
                    player.playSingle(rect);
                } catch (InterruptedException | LineUnavailableException | IOException | UnsupportedAudioFileException ex) {
                    Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                } 
            }
            mainPanel.unselectNotes();
            mainPanel.repaint();
        });
        
        JRadioButton sharpButton = new JRadioButton("Sharp");
        JRadioButton flatButton = new JRadioButton("Flat");        
        JRadioButton naturalButton = new JRadioButton("Natural", true); 
        
        
        ButtonGroup accidentals = new ButtonGroup();
        accidentals.add(sharpButton);
        accidentals.add(flatButton);
        accidentals.add(naturalButton);
        
        optionsMenu.add(playSingleNoteButton);
        optionsMenu.addSeparator();
        optionsMenu.add(sharpButton);
        optionsMenu.add(flatButton);
        optionsMenu.add(naturalButton);

        optionsMenu.setPopupSize(200, 200);        
        add(optionsMenu);
        
        bar.addMeasure();
        
        //Add panels
        add(toolbar, BorderLayout.NORTH);
        add(new JScrollPane(mainPanel), BorderLayout.CENTER);
        
        setJMenuBar(menuBar);
        
        file_manager = new CustomFileManager();
        
        pack();
        setVisible(true);
    }
    
    public void saveFile() {
        if(file_manager.currentFileHasName()) {
            //Save to that file
            file_manager.createJSONFromBar(bar);
            file_manager.writeToFile(file_manager.getCurrentFileName());
        } else {
            saveAsFile();
        }
        
        
        System.out.println("save file reached");
    }
    
    public void saveAsFile() {
        System.out.println("save as file reached"); 
        
        String filename = null;
        
        //Prompt user with window to select where to save file
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("JSON Files", "json");
        chooser.setFileFilter(filter);
        
        int button_pressed_val = chooser.showOpenDialog(this);
        if(button_pressed_val == JFileChooser.APPROVE_OPTION) {
            System.out.println("This file was chosen: " + chooser.getSelectedFile().getAbsolutePath());
            filename = chooser.getSelectedFile().getAbsolutePath();
            //Create JSON Object from Bar Object
            file_manager.createJSONFromBar(bar);

            //Write to file
            if (filename != null) {
                file_manager.writeToFile(filename);
            } else {
                System.out.println("filename is null");
            }
        }
       
        
        
        
    }
    
    public void openAnotherFile() {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("JSON Files", "json");
        chooser.setFileFilter(filter);
        int button_pressed_val = chooser.showOpenDialog(this);
        if(button_pressed_val == JFileChooser.APPROVE_OPTION) {
            reset();
            try {
            bar = file_manager.createBarFromJSON(chooser.getSelectedFile().getAbsolutePath());
            
            mainPanel.updateWithBar(bar, mainPanel.getGraphics());
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
        
        System.out.println("open file reached");        
    }
    
    public void openNewProject() {
        
        //Prompt user to check if they really want to delete without saving
        
            //If yes, delete everything current in bar
        reset();
        
            //Open file
        
        System.out.println("open new file reached");
    }
    
    private void reset() {
        bar.reset();
        mainPanel.reset();
        file_manager.reset();
    }
    
    public void testMe() {
        System.out.println("hello!!!!");
    }

    private void createMenuBar() {
        menuBar = new JMenuBar();
        
        JMenu fileMenu = new JMenu("File");
        JMenuItem newButton = new JMenuItem("New Project");
        JMenuItem saveButton = new JMenuItem("Save");
        JMenuItem saveAsButton = new JMenuItem("Save as...");
        JMenuItem openButton = new JMenuItem("Open project...");
        
        newButton.addActionListener(e -> {
           openNewProject(); 
        });
        
        saveButton.addActionListener(e -> {
           saveFile(); 
        });
        
        saveAsButton.addActionListener(e -> {
            saveAsFile();
        });
        
        openButton.addActionListener(e -> {
           openAnotherFile(); 
        });
        
        fileMenu.add(newButton);
        fileMenu.add(saveButton);
        fileMenu.add(saveAsButton);
        fileMenu.add(openButton);
        
        menuBar.add(fileMenu);    
    }
}
