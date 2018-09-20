/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.johnsonclayton.collections;

import java.util.ArrayList;

/**
 *
 * @author clayt
 */
class Items extends ArrayList<Item>{

    boolean hasItemByName(String item_name) {
        for(Item item : this) {
            if(item.name.equals(item_name))
                return true;
        }
        return false;
    }
    
    int totalInCents() {
        int sum = 0;
        for (Item item : this) {
            sum += item.priceInCents;
        }
        return sum;
    }
    
}
