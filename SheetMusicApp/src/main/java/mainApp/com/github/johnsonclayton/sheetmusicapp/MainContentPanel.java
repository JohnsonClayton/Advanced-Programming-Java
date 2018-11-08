/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainApp.com.github.johnsonclayton.sheetmusicapp;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author clayt
 */
class MainContentPanel extends JPanel{
    ArrayList<Rectangle> rectangles;
    MainContentPanel() {
        setLayout(new BorderLayout());
        rectangles = new ArrayList<Rectangle>();
        
        initRectangles();
     
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Mouse clicked");
                for(Rectangle rect : rectangles) {
                    if(rect.containsClick(e.getX(), e.getY())) {
                        rect.filled = !rect.filled;
                        repaint();
                        break;
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println("Mouse pressed");
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                System.out.println("Mouse released");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                System.out.println("Mouse entered");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                System.out.println("Mouse exited");
            }
        });
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(1900, 2000);
    }
    
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
        //Width of 420 (blaze it)
        int x = 100, y = 100, width = 420, height = 50;
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
    
    private void draw(Graphics g, Rectangle rect) {
        if(rect.filled) {
            g.fillOval(rect.x - 12, rect.y - 12, rect.width * 2, rect.height * 2 - 10); //For appearing to take up a whole space
        }
        else {
            g.drawRect(rect.x, rect.y, rect.width, rect.height);
        }
    }

    private void initRectangles() {
        int x = 150, x_const = x, y = 90, y_const = y, width = 25, x_spacing = 100, height = 25;
        
        rectangles.add(new Rectangle(x, y, width, height));
        y+= height;
        rectangles.add(new Rectangle(x, y, width, height));
        y+= height;
        rectangles.add(new Rectangle(x, y, width, height));
        y+= height;
        rectangles.add(new Rectangle(x, y, width, height));
        y+= height;
        rectangles.add(new Rectangle(x, y, width, height));
        y+= height;
        rectangles.add(new Rectangle(x, y, width, height));
        y+= height;
        rectangles.add(new Rectangle(x, y, width, height));
        y+= height;
        rectangles.add(new Rectangle(x, y, width, height));
        y+= height;
        rectangles.add(new Rectangle(x, y, width, height));
        
        x+=x_spacing;
        y = y_const;
        rectangles.add(new Rectangle(x, y, width, height));
        y+= height;
        rectangles.add(new Rectangle(x, y, width, height));
        y+= height;
        rectangles.add(new Rectangle(x, y, width, height));
        y+= height;
        rectangles.add(new Rectangle(x, y, width, height));
        y+= height;
        rectangles.add(new Rectangle(x, y, width, height));
        y+= height;
        rectangles.add(new Rectangle(x, y, width, height));
        y+= height;
        rectangles.add(new Rectangle(x, y, width, height));
        y+= height;
        rectangles.add(new Rectangle(x, y, width, height));
        y+= height;
        rectangles.add(new Rectangle(x, y, width, height));
        
        x+=x_spacing;
        y = y_const;
        rectangles.add(new Rectangle(x, y, width, height));
        y+= height;
        rectangles.add(new Rectangle(x, y, width, height));
        y+= height;
        rectangles.add(new Rectangle(x, y, width, height));
        y+= height;
        rectangles.add(new Rectangle(x, y, width, height));
        y+= height;
        rectangles.add(new Rectangle(x, y, width, height));
        y+= height;
        rectangles.add(new Rectangle(x, y, width, height));
        y+= height;
        rectangles.add(new Rectangle(x, y, width, height));
        y+= height;
        rectangles.add(new Rectangle(x, y, width, height));
        y+= height;
        rectangles.add(new Rectangle(x, y, width, height));
        
        x+=x_spacing;
        y = y_const;
        rectangles.add(new Rectangle(x, y, width, height));
        y+= height;
        rectangles.add(new Rectangle(x, y, width, height));
        y+= height;
        rectangles.add(new Rectangle(x, y, width, height));
        y+= height;
        rectangles.add(new Rectangle(x, y, width, height));
        y+= height;
        rectangles.add(new Rectangle(x, y, width, height));
        y+= height;
        rectangles.add(new Rectangle(x, y, width, height));
        y+= height;
        rectangles.add(new Rectangle(x, y, width, height));
        y+= height;
        rectangles.add(new Rectangle(x, y, width, height));
        y+= height;
        rectangles.add(new Rectangle(x, y, width, height));
    }
}
