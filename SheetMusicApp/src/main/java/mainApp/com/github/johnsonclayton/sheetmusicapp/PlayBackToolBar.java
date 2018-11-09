/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainApp.com.github.johnsonclayton.sheetmusicapp;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.JPanel;


/**
 *
 * @author clayt
 */
class PlayBackToolBar extends JPanel{
    JButton playButton;
    JButton pauseButton;
    JButton stopButton;
    JButton newMeasureButton;
    
    CommandListener cmdListener;
    
    PlayBackToolBar() {
        //Add to toolbar
        playButton = new JButton("Play");
        pauseButton = new JButton("Pause");
        stopButton = new JButton("Stop");
        newMeasureButton = new JButton("New Measure");
        
        
        //Add listeners for toolbar
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cmdListener.commandRequested(Util.PLAY_MUSIC);
            }
        });
        
        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cmdListener.commandRequested(Util.PAUSE_MUSIC);
            }
        });
        
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cmdListener.commandRequested(Util.STOP_MUSIC);
            }
        });
        
        newMeasureButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cmdListener.commandRequested(Util.ADD_MEASURE);
            }
        });
        
        setLayout(new FlowLayout(FlowLayout.LEFT));
        add(playButton);
        add(pauseButton);
        add(stopButton);
        add(newMeasureButton);
    }

    public void setCommandListener(CommandListener commandListener) {
        this.cmdListener = commandListener;
    }
}
