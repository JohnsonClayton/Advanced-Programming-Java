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
class Item {
    int priceInCents;
    String name;
    
    Item(String _name,int _priceInCents) {
        priceInCents = _priceInCents;
        name = _name;
    }
}