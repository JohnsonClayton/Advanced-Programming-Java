/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.johnsonclayton.markettrading;

/**
 *
 * @author clayt
 */
public class Stock {

    double price;
    String name;
    int shares;
    
    void setPrice(double _price) {
        price = _price;
    }

    void setName(String _name) {
        name = _name;
    }

    void setNumberOfShares(int _shares) {
        shares = _shares;
    }
    
}
