package mainApp.com.github.johnsonclayton.sheetmusicapp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;



public class mainApp extends JPanel implements ActionListener, MouseListener{
    private static JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem menuItem;
    
    private JToolBar toolBar;
    
    protected CustomPlayer theDJ;
    protected Bar bar;
    
    public mainApp() {
        
        //Toolbar
        toolBar = new JToolBar("Editing ToolBar");
        //toolBar.setFloatable(false);
        
        addButtons(toolBar);
        
        toolBar.setOrientation(JToolBar.VERTICAL);
        toolBar.setVisible(true);
        add(toolBar, BorderLayout.PAGE_START);
        
        //Menu
        menuBar = new JMenuBar();
        
        menu = new JMenu("Test JMenu 1");
        
        menuItem = new JMenuItem("Menu Item 1");
        menu.add(menuItem);
        
        menuItem = new JMenuItem("Menu Item 2");
        menu.add(menuItem);
        
        menuItem = new JMenuItem("Menu Item 3");
        menu.add(menuItem);
        
        menuBar.add(menu);
        
        
        
        
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
        
        this.setBackground(Color.WHITE);
        
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
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException ex) {
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

        //frame.add(toolBar, BorderLayout.PAGE_START);
        //newContentPane.add(toolBar, BorderLayout.PAGE_START);
        //frame.add(toolBar, BorderLayout.PAGE_START);
        
        frame.setJMenuBar(menuBar);

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
        this.getGraphics().fillOval(e.getX() - (e.getX() % 25), e.getY() - (e.getY() % 25), 50, 50);
    }   
    
    @Override
    public void paint(Graphics g) {
        g.drawLine(175, 200, 1750, 200);
        g.drawLine(175, 250, 1750, 250);
        g.drawLine(175, 300, 1750, 300);
        g.drawLine(175, 350, 1750, 350);
        g.drawLine(175, 400, 1750, 400);
    }

    private void addButtons(JToolBar toolBar) {
        JButton button = null;
        
        button = new JButton("Play");
        button.addActionListener(this);
        button.setActionCommand("play");
        
        toolBar.add(button);
        
        button = new JButton("Pause");
        button.addActionListener(this);
        button.setActionCommand("pause");
        
        toolBar.add(button);
        
        button = new JButton("Stop");
        button.addActionListener(this);
        button.setActionCommand("stop");
        
        toolBar.add(button);
    }
}