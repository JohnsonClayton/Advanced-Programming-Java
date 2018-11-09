/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainApp.com.github.johnsonclayton.sheetmusicapp;

/**
 *
 * @author clayt
 */
public class CommandListener {
    CommandListener() {};
    CommandListener(Bar bar) {};
    public void commandRequested(int cmd){};
    public void commandRequested(int cmd, Rectangle rect){};
}
