/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.johnsonclayton.threadsproject;

import java.util.List;

/**
 *
 * @author clayt
 */
public class SerializedAction implements Action{
    Action action;
    private Object lock = new Object();
    
    SerializedAction(Action _action) {
        action = _action;
    }

    @Override
    public void actOn(List list) {
        synchronized(lock) {
            System.out.println("acting on " + Integer.toHexString(System.identityHashCode(list)));
            action.actOn(list);
            System.out.println("finished on " + Integer.toHexString(System.identityHashCode(list)));
        }        
    }
    
    //Don't do the following...
    /*public synchronized void actOn(List list) {
        synchronized(this) {
            //What if you are yourself are someone's botton? What happens when you control access to yourself when you have no control over yourself?
        }
    }*/
    
}
