/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.johnsonclayton.markettrading;

import java.util.HashMap;

/**
 *
 * @author clayt
 */
class Customer {
    public double moneyInDollars;
    public HashMap<String, Integer> stocksHeld;

    void setMonies(double _monies) {
        moneyInDollars = _monies;
        stocksHeld = new HashMap<>();
    }

    void addToCollection(Stock bought) {
        if(stocksHeld.containsKey(bought.name)) {
            int count = (stocksHeld.get(bought.name)+1);
            stocksHeld.put(bought.name, count);
        }
        else {
            stocksHeld.put(bought.name, 0);
        }
    }
}
