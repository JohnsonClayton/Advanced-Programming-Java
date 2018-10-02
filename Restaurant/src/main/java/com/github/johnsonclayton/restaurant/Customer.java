/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.johnsonclayton.restaurant;

/**
 *
 * @author clayt
 */
class Customer {
    private String name;
    Restaurant restaurant;
    private int moneyUSD;
    public MenuItems menu_items_ordered;
    Customer(String _name, int _moneyUSD) {
        name = _name;
        moneyUSD = _moneyUSD;
        menu_items_ordered = new MenuItems();
    }

    void order(MenuItem menu_item) {
        if(restaurant.hasIngredientsFor(menu_item)) {
            System.out.println("Yo, we got you a sandwhich");
            menu_items_ordered.add(menu_item);
        }
        else
            throw new IllegalArgumentException("Restaurant cannot make a " + menu_item.getName());
        
    }
    
    void setRestaurant(Restaurant _restaurant) {
        restaurant = _restaurant;
    }

    void payForMeal() {
        int totalBalance = 0;
        for (MenuItem menu_item : menu_items_ordered) {
            totalBalance += menu_item.priceUSD;
        }
        if(totalBalance > moneyUSD) {
            restaurant.customers_dishwashing.add(this);
        } else
            moneyUSD -= totalBalance;
    }
    
}
