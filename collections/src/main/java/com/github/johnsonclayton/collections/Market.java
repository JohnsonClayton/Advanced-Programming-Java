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
public class Market {
    Items items;
    //Registers registers;
    //Customers customers;
    Market() {
        items = new Items();
    }

    void addItem(String item_name, int item_price) {
        items.add(new Item(item_name, item_price));
    }

    void printSales(int discountInPercent) {
        
    }

    boolean hasItemByName(String item_name) {
        return items.hasItemByName(item_name);
    }

    Cart getCartFor(Customer customer) {
        return new Cart(this, customer);
    }
}
