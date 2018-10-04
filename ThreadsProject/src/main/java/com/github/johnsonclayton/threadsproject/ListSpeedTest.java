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
    ArrayList<String> arrayList = new ArrayList<String>();
    LinkedList<String> linkedList = new LinkedList<String>();
    TestWorker arrayWorker;
    TestWorker linkedWorker;
    void setAction(SortAction _action) {
        action = _action;
    }

    void start() {
        Action rando = new RandomizeAction(1_000_000);
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
