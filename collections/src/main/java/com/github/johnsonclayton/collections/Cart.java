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
class Cart {
    Customer customer;
    Market market;
    Items items = new Items();

    Cart(Market _market, Customer _customer) {
        market = _market;
        customer = _customer;
    }
    void addItem(String item_name) {
        for(Item marketItem : market.items) {
            if (marketItem.name.equals(item_name)) {
                items.add(marketItem);
                market.items.remove(marketItem);
                return;
            }
        }
        throw new IllegalStateException("item " + item_name + " does not exist in market");
    }

    void checkout() {
        if(items.totalInCents() <= customer.moneyInCents) {
            customer.moneyInCents -= items.totalInCents();
            customer.items.addAll(items);
            items.clear();
        } else {
            throw new IllegalStateException("customer does not have enough money!");
        }
    }

    boolean hasItemByName(String item_name) {
        return items.hasItemByName(item_name);
    }
    
}
