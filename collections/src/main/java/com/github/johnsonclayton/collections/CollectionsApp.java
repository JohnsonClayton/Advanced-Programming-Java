/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.johnsonclayton.collections;

/**
 *
 * @author clayt
 */
public class CollectionsApp {
    public static void main(String[] args) throws Exception {
        CollectionsApp app = new CollectionsApp();
        app.run();
    }

    private void run() {
        Market market = new Market();
        market.addItem("cheese", 100);
        market.addItem("solar panel powerstrip", 33);
        market.printSales(10);
    }
}
