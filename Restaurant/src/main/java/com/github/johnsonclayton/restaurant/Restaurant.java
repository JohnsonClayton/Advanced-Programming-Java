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
public class Restaurant {
    private Customers customers;
    private MenuItems menu_items;
    private int open_tables;
    private Ingredients ingredientsInStock;
    private Customers customers_waiting;
    
    public Restaurant(MenuItems _menu_items, int total_tables, Ingredients _ingredientsInStock) {
        customers = new Customers();
        menu_items = _menu_items;
        open_tables = total_tables;
        ingredientsInStock = _ingredientsInStock;
        customers_waiting = new Customers();
    }
    
    public static void main(String []args) throws Exception{
        System.out.println("Hello, world!");
        
        
        
    }

    void addCustomer(Customer customer) {
        customer.setRestaurant(this);
        if(open_tables > 0) {
            customers.add(customer);
            open_tables--;
        }
        else if(open_tables == 0) {
            customers_waiting.add(customer);
        }
        else {
            throw new IllegalStateException("Open Tables in restaurant may not be below zero!");
        }
    }

    int getCustomerCount() {
        return customers.size();
    }

    int getCustomerWaitingCount() {
        return customers_waiting.size();
    }

    void removeCustomer(Customer customer) {
        if(customers.contains(customer)) {
            customers.remove(customer);
            
            if(this.getCustomerWaitingCount() > 0)
            {
                customers.add(customers_waiting.getFirst());
                customers_waiting.remove(customers_waiting.getFirst());
            }
            else
                ++open_tables;
        }
        else
            throw new IllegalArgumentException("Cannot remove a customer that is not in the restaurant!");
    }

    int getAvailableTables() {
       return open_tables; 
    }

    boolean hasIngredientsFor(MenuItem menu_item) {
        Ingredients ingredientsFound = new Ingredients();
        for (Ingredient ingredient : menu_item.getIngredients()) {
            boolean found = false;
            for (Ingredient ingredientStocked : ingredientsInStock) {
                if(ingredientStocked.name.equals(ingredient.name)) {
                    found = true;
                    ingredientsFound.add(ingredientStocked);
                }
            }
            if(!found) return false;
        }
        
        for (Ingredient ingredient : ingredientsFound) {
            if(ingredient.count <= 1) {
                ingredientsInStock.remove(ingredient);
            }
            else
                --ingredient.count;
        }
        return true;
    }    
}
