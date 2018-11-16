/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainApp.com.github.johnsonclayton.sheetmusicapp;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

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
    public MouseEvent mouseEvent;
    
    MainContentPanel() {
        setLayout(new BorderLayout());
        rectangles = new ArrayList<>();
        measures = new ArrayList<>();
        panelHeight = 1500;
        
        addMeasure();
                
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //System.out.println("Mouse clicked");
                for(Rectangle rect : rectangles) {
                    if(rect.containsMouse(e.getX(), e.getY())) {
                        
                        System.out.println(e.getButton());
                        
                        //Button 1 should add (Primary Button)
                        if(e.getButton() == 1 && !rect.filled) {
                            rect.filled = true;
                            unselectNotes();
                            commandListener.commandRequested(Util.ADD_NOTE, rect);
                            repaint();
                        }
                        else if(e.getButton() == 1 && rect.filled && !rect.grayed) {
                            rect.filled = false;
                            
                            repaint();

                            //Send command that note was clicked
                            commandListener.commandRequested(Util.ADD_NOTE, rect);                        

                            //Send command to add note and attach to this rectangle
                            //System.out.println("Note added: " + rect.note_val);
                        }
                        else if(e.getButton() == 1 && rect.filled && rect.grayed) {
                            rect.grayed = false;
                            repaint();
                        }
                        
                        //Button 3 should create menu
                        if(e.getButton() == 3 && rect.filled) {
                            rect.grayed = true;
                            repaint();
                            
                            mouseEvent = e;
                            commandListener.commandRequested(Util.POP_UP_TRIGGERED);
                            
                           
                            System.out.println("created menu");
                        } 
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
        
        
        try {
            bassClef = ImageIO.read(new File(Util.bass_clef));
            trebleClef = ImageIO.read(new File(Util.treble_clef));
        } catch (IOException ex) {
            System.out.println("I guess you don't get to enjoy the lovely images I've prepared for you...");
        }
        
        
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(1900, panelHeight);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        System.out.println("Size of rectangles in painComponent: " + rectangles.size());
        
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
        if(rect.grayed) {
            g.setColor(Color.gray);
        }
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
        g.setColor(Color.black);
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

    public Rectangle getSelectedNote() {
        Rectangle rect = null;
        for(Rectangle rectangle : rectangles) {
            if(rectangle.grayed) {
                rect = rectangle;
            }
        }
        return rect;
    }

    void unselectNotes() {
        for(Rectangle rect : rectangles) {
            rect.grayed = false;
        }
    }

    void reset() {
        rectangles.clear();
        measures.clear();
        panelHeight = 1500;

        addMeasure();
        
        try {
            bassClef = ImageIO.read(new File(Util.bass_clef));
            trebleClef = ImageIO.read(new File(Util.treble_clef));
        } catch (IOException ex) {
            Logger.getLogger(MainContentPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("Size of rectangles before repaint: " + rectangles.size());
        repaint();
        System.out.println("Size of rectangles after repaint: " + rectangles.size());
    }

    void updateWithBar(Bar bar, Graphics g) {
        //Update GUI based on what Bar looks like at this point
        
        for(int i = 0; i < bar.getMeasures().size(); i++) {
            addMeasure();
        }
        
        measures = bar.getMeasures();
       
        this.update(g);
        System.out.println("Size of rectangles in updateWithBar : " + rectangles.size());

        /*for(Measure measure : measures) {
            //System.out.println("Entered measures loop");
            for(ArrayList<Note> notes : measure.beats) {
                //System.out.println("\tEntered notes loop");
                for(Note note : notes) { //Why doesn't rectangles have any elements?
                    //System.out.println("\t\tEntered rect loop");
                    for(Rectangle rect : rectangles) {
                        //if(rect.beat == notes.indexOf(note) && rect.note_val == note.value) {
                        System.out.println("Comparing...");
                        if(rect.beat == measure.beats.indexOf(notes)&& rect.note_val == note.value) {
                            rect.filled = true;
                            System.out.println("Note placed from file: " + note.value);
                        }
                    }
                }
            }
        }*/
        Measure measure;
        int counter = 0;
        for(Rectangle rect : rectangles) {
            measure = measures.get(rect.measure_id);
            
            counter = 0;
            for(ArrayList<Note> beat : measure.beats) {
                counter++;
                for(Note note : beat) {
                    if(note.value == rect.note_val && counter == rect.beat) {
                        rect.filled = true;
                    }
                }
            }
        }
        
        repaint();
    }

    
}
