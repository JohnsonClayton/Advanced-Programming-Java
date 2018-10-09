/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.johnsonclayton.threadsproject;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author clayt
 */
public class ListSpeedTest {
    
    Action action;
    public static final int DEFAULT_SIZE = 1_000_000;
    int size;
    
    ArrayList<String> arrayList = new ArrayList<String>();
    LinkedList<String> linkedList = new LinkedList<String>();
    TestWorker arrayWorker;
    TestWorker linkedWorker;
    
    ListSpeedTest(Action _action, int _size) {
        action = _action;
        size = _size;
    }
    
    
    ListSpeedTest() {
        action = null;
        size = DEFAULT_SIZE;
    }
    
    void setAction(Action _action) {
        action = _action;
    }

    void start() {
        Action rando = new RandomizeAction(size);
        rando.actOn(arrayList);
        rando.actOn(linkedList);
        arrayWorker = new TestWorker(arrayList, action);
        linkedWorker = new TestWorker(linkedList, action);
        arrayWorker.start();
        linkedWorker.start();
        
    }

    void waitTilFinish() {
        arrayWorker.waitTilDone();
        linkedWorker.waitTilDone();
    }
    
    String outcome() {
        if(arrayWorker.millis < linkedWorker.millis) {
            long delta = -1 * (arrayWorker.millis - linkedWorker.millis);
            return "array won by " + delta + " milliseconds.";
        }
        else {
            long delta = (arrayWorker.millis - linkedWorker.millis);
            return "linked won by " + delta + " milliseconds.";
        }
    }
    
}
