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
public class Customer {
    private int orderNumer;
    String name;
    int moneyInCents;
    Items items = new Items();
    
    Customer(String _name, int _moneyInCents){
        name = _name;
        moneyInCents = _moneyInCents;
    };

    boolean hasItemByName(String item_name) {
        return items.hasItemByName(item_name);
    }
}
