/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.JohnsonClayton;

/**
 *
 * @author clayt
 */
public class HelloWorld {
    public static void main(String []args) throws Exception {
        HelloWorld helloWorld = new HelloWorld();
        helloWorld.setMessage("Hello, world!");
        helloWorld.sayMessage();
    }
    
    private String message = "";
    
    private void sayMessage() {
        System.out.println(getMessage());
    }
    
    public void setMessage(String _message) {
        if (_message != null)
            this.message = _message;
        else {
            throw new UnsupportedOperationException("Message cannot be null.");
        }
    }
    
    public String getMessage() {
        return this.message;
    }
}
