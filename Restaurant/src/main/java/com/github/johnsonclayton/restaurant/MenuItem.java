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
class MenuItem {
    private Ingredients ingredients;
    private String name;
    public int priceUSD;

    public MenuItem(String _name, int _priceUSD) {
        ingredients = new Ingredients();
        name = _name;
        priceUSD = _priceUSD;
    }
    
    void addToIngredients(String ingredient_name) {
        ingredients.add(new Ingredient(ingredient_name, 1));
    }

    Ingredients getIngredients() {
        return ingredients;
    }

    String getName() {
        return name;
    }
}
