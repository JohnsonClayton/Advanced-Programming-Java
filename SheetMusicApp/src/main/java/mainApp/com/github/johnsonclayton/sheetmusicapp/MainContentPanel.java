/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainApp.com.github.johnsonclayton.sheetmusicapp;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author clayt
 */
class MainContentPanel extends JPanel{
    ArrayList<Rectangle> rectangles;
    ArrayList<Measure> measures;
    int panelHeight;
    private CommandListener commandListener;
    private BufferedImage bassClef;
    private BufferedImage trebleClef;
    private Graphics2D g2;
    
    MainContentPanel() {
        setLayout(new BorderLayout());
        rectangles = new ArrayList<>();
        measures = new ArrayList<>();
        panelHeight = 1500;
                
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //System.out.println("Mouse clicked");
                for(Rectangle rect : rectangles) {
                    if(rect.containsMouse(e.getX(), e.getY())) {
                        rect.filled = !rect.filled;
                        repaint();
                        
                        //Send command that note was clicked
                        commandListener.commandRequested(4, rect);                        
                        
                        //Send command to add note and attach to this rectangle
                        //System.out.println("Note added: " + rect.note_val);
                        
                        break;
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                //System.out.println("Mouse pressed");
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                //System.out.println("Mouse released");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                
            }

            @Override
            public void mouseExited(MouseEvent e) {
                //System.out.println("Mouse exited");
            }
        });
        addMeasure();
        
        try {
            bassClef = ImageIO.read(new File(Util.bass_clef));
            trebleClef = ImageIO.read(new File(Util.treble_clef));
        } catch (IOException ex) {
            Logger.getLogger(MainContentPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(1900, panelHeight);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        paintBar(g);
        paintNotes(g);
    }
    
    public void paintNotes(Graphics g) {
        for(Rectangle rect : rectangles) {
            draw(g, rect);
        }
    }

    public void paintBar(Graphics g) {
        int size = measures.size();
        int     width = 420, 
                height = 50, 
                init_x = 100, 
                init_y = 100,
                measure_separation = 650,
                x_counter = 0,
                x,
                y = init_y;
        
        int     bx = 150, 
                bx_const = bx, 
                by = 65, 
                by_const = by, 
                bwidth = 25, 
                bx_spacing = 100, 
                bheight = 25;
        
        
        for(int i = 0, row = 0; i < size; i++, x_counter++) {
            //Keeps track of how many rows
            if(i != 0 && i % 4 == 0) {
                row++;
                x = init_x;
                x_counter = 0;
            }
            else {
                x = init_x + (x_counter * width);
            }
            y = init_y + (row * measure_separation);
            if(x_counter == 0) {
                paintClefs(g, x, y);
            }

            addMeasureHitBoxes(x + 50, bx_const, y - 35, y - 35, bwidth, bx_spacing, bheight, i);
            drawLines(g, x, y, width, height);
            
        }
    }
    
    private void draw(Graphics g, Rectangle rect) {
        if(rect.filled) {
            g.fillOval(rect.x - 12, rect.y - 12, rect.width * 2, rect.height * 2 - 10); //For appearing to take up a whole space
            //g.drawLine(rect.x - 12, rect.y + (rect.height / 2), rect.x - 12, (rect.y + (rect.height / 2) ) + 200);
            if((rect.note_val >= Util.D_1 && rect.note_val <= Util.C_2) || !(rect.note_val >= Util.D_2 && rect.note_val <= Util.A_3)) {
                g.fillRect(rect.x - 12, rect.y + (rect.height / 2) - 5, 5, 100);
            }
            else {
                g.fillRect(rect.x - 17 + (2 * rect.width), rect.y + (rect.height / 2) - 100, 5, 100);
            }
            //g.drawLine(rect.x, 200, 200);
        }
        else {
            //g.drawRect(rect.x, rect.y, rect.width, rect.height);
        }
    }
    
    void addMeasureHitBoxes(int x, int x_const, int y, int y_const, int width, int x_spacing, int height, int measure_id) {
        int currentNoteVal = Util.G_4;

        for(int i = 0; i < 4;   i++, 
                                x+=x_spacing,
                                y = y_const) {
            //Add new beat hit boxes
            addBeatHitBoxes(x, y, width, height, currentNoteVal, measure_id, i);
        }
    }

    void addBeatHitBoxes(int x, int y, int width, int height, int currentNoteVal, int measure_id, int beat) {
        rectangles.add(new Rectangle(x, y, width, height, currentNoteVal, measure_id, beat));
        y+= height;
        currentNoteVal--;
        rectangles.add(new Rectangle(x, y, width, height, currentNoteVal, measure_id, beat));
        y+= height;
        currentNoteVal--;
        rectangles.add(new Rectangle(x, y, width, height, currentNoteVal, measure_id, beat));
        y+= height;
        currentNoteVal--;
        rectangles.add(new Rectangle(x, y, width, height, currentNoteVal, measure_id, beat));
        y+= height;
        currentNoteVal--;
        rectangles.add(new Rectangle(x, y, width, height, currentNoteVal, measure_id, beat));
        y+= height;
        currentNoteVal--;
        rectangles.add(new Rectangle(x, y, width, height, currentNoteVal, measure_id, beat));
        y+= height;
        currentNoteVal--;
        rectangles.add(new Rectangle(x, y, width, height, currentNoteVal, measure_id, beat));
        y+= height;
        currentNoteVal--;
        rectangles.add(new Rectangle(x, y, width, height, currentNoteVal, measure_id, beat));
        y+= height;
        currentNoteVal--;
        rectangles.add(new Rectangle(x, y, width, height, currentNoteVal, measure_id, beat));
        y+= height;
        currentNoteVal--;
        rectangles.add(new Rectangle(x, y, width, height, currentNoteVal, measure_id, beat));
        y+= height;
        currentNoteVal--;
        rectangles.add(new Rectangle(x, y, width, height, currentNoteVal, measure_id, beat));
        y+= height;
        currentNoteVal--;
        rectangles.add(new Rectangle(x, y, width, height, currentNoteVal, measure_id, beat));
        y+= height;
        currentNoteVal--;
        rectangles.add(new Rectangle(x, y, width, height, currentNoteVal, measure_id, beat));
        y+= height;
        currentNoteVal--;
        rectangles.add(new Rectangle(x, y, width, height, currentNoteVal, measure_id, beat));
        y+= height;
        currentNoteVal--;
        rectangles.add(new Rectangle(x, y, width, height, currentNoteVal, measure_id, beat));
        y+= height;
        currentNoteVal--;
        rectangles.add(new Rectangle(x, y, width, height, currentNoteVal, measure_id, beat));
        y+= height;
        currentNoteVal--;
        rectangles.add(new Rectangle(x, y, width, height, currentNoteVal, measure_id, beat));
        y+= height;
        currentNoteVal--;
        rectangles.add(new Rectangle(x, y, width, height, currentNoteVal, measure_id, beat));
        y+= height;
        currentNoteVal--;
        rectangles.add(new Rectangle(x, y, width, height, currentNoteVal, measure_id, beat));
        y+= height;
        currentNoteVal--;
        rectangles.add(new Rectangle(x, y, width, height, currentNoteVal, measure_id, beat));
        y+= height;
        currentNoteVal--;
        rectangles.add(new Rectangle(x, y, width, height, currentNoteVal, measure_id, beat));
        y+= height;
        currentNoteVal--;
        rectangles.add(new Rectangle(x, y, width, height, currentNoteVal, measure_id, beat));
        y+= height;
        currentNoteVal--;
        rectangles.add(new Rectangle(x, y, width, height, currentNoteVal, measure_id, beat));
    }
    
    void addMeasure() {
        measures.add(new Measure());
        panelHeight += 100;
        
        //System.out.println("Measure added");
    }

    private void drawLines(Graphics g, int x, int y, int width, int height) {
        //Vertical Bars at Start and End
        g.drawLine(x, y, x, y + height * 10);
        g.drawLine(x + width, y, x + width, y + height * 10);

        //Horizontal Bars
        g.drawLine(x, y, x+width, y);
        y+= height;
        g.drawLine(x, y, x+width, y);
        y+= height;
        g.drawLine(x, y, x+width, y);
        y+= height;
        g.drawLine(x, y, x+width, y);
        y+= height;
        g.drawLine(x, y, x+width, y);

        y+= height;
        y+= height;

        g.drawLine(x, y, x+width, y);
        y+= height;
        g.drawLine(x, y, x+width, y);
        y+= height;
        g.drawLine(x, y, x+width, y);
        y+= height;
        g.drawLine(x, y, x+width, y);
        y+= height;
        g.drawLine(x, y, x+width, y);    
    }

    void setCommandListener(CommandListener _listener) {
        commandListener = _listener;
    }

    private void paintClefs(Graphics g, int x, int y) {
        //System.out.println("clefs added");
        g2 = (Graphics2D) g;
        g2.scale(0.1, 0.1);
        //g2.drawImage(trebleClef, 10 * (x - 550), 10 * (y + 900), this);
        g2.drawImage(trebleClef, 10 * (x - 150), 10 * (y), this);

        g2.scale(2, 2);
        //g2.drawImage(bassClef, x - 120, y + 2000, this);
        g2.drawImage(bassClef, 5 * (x - 100), 5 * (y + 350), this);
        g2.scale(5, 5);
        //g.drawImage(bassClef, x, y + 250, this);
    }
}
