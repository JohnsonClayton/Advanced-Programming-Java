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
    Customer(String _name) {
        name = _name;
    }

    void order(MenuItem menu_item) {
        if(restaurant.hasIngredientsFor(menu_item)) {
            System.out.println("Yo, we got you a sandwhich");
        }
        else
            throw new IllegalArgumentException("Restaurant cannot make a " + menu_item.getName());
        
    }
    
    void setRestaurant(Restaurant _restaurant) {
        restaurant = _restaurant;
    }
    
}
