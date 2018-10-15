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
public class Market {
    public HashMap<String, Stock> market;
    //need timer
    
    Market() {
        market = new HashMap<>();
    }

    void addStockToMarket(Stock _stock) {
        market.put(_stock.name, _stock);
    }

    int sharesOf(String _stock) {
        return market.get(_stock).shares;
    }

    double getPrice(String _stock) {
        return market.get(_stock).price;
    }
    
    
}
