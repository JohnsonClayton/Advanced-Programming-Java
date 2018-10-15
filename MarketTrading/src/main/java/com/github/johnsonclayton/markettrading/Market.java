/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.johnsonclayton.markettrading;

import java.util.HashMap;

/**
 *
 * @author clayt
 */
public class Market {
    public HashMap<String, Stock> market;
    public Customers customers;
    private Object lock = new Object();
    
    Market() {
        market = new HashMap<>();
        customers = new Customers();
    }

    void addStockToMarket(Stock _stock) {
        market.put(_stock.name, _stock);
    }

    int sharesOf(String _name) {
        if(market.containsKey(_name))
            return market.get(_name).shares;
        else 
            throw new IllegalArgumentException();
    }

    double getPrice(String _name) {
        if(market.containsKey(_name))
            return market.get(_name).price;
        else
            throw new IllegalArgumentException();
    }

    void removeStockFromMarketByName(String _name) {
        removeStockFromMarketByObject(market.get(_name));
    }

    void removeStockFromMarketByObject(Stock _stock) {
        market.remove(_stock.name);
    }

    void stockPurchase(Customer _customer, String _name, int _shareCount) {
        if(market.containsKey(_name)) {
            synchronized(lock) {
                Stock beingBought = market.get(_name);
                for(int i = 0; i < _shareCount && _customer.moneyInDollars > beingBought.price && beingBought.shares > 0; ++i) {
                    _customer.moneyInDollars -= beingBought.price;
                    _customer.addToCollection(beingBought);
                    --beingBought.shares;
                }
            }
        }
        else
            throw new IllegalArgumentException();
    }

    void stockSell(Customer _customer, String _name, int _shareCount, double _price) {
        if(_customer.stocksHeld.containsKey(_name)) {
            synchronized(lock) {
                for(int i = 0; i < _shareCount && _customer.stocksHeld.containsKey(_name); ++i) {
                    if(!market.containsKey(_name)) {
                        //Make new stock object
                        Stock soldStock = new Stock();
                        soldStock.setName(_name);
                        soldStock.setPrice(_price);
                        soldStock.setNumberOfShares(0);
                        market.put(_name, soldStock);
                    }
                    _customer.moneyInDollars += _price;
                    int prevNum = _customer.stocksHeld.get(_name);
                    _customer.stocksHeld.put(_name, --prevNum);
                    ++market.get(_name).shares;
                }
            }
        }
        else
            throw new IllegalArgumentException();
    }
    
    void stockSell(Customer _customer, String _name, int _shareCount) {
        if(_customer.stocksHeld.containsKey(_name)) {
            synchronized(lock) {
                for(int i = 0; i < _shareCount && _customer.stocksHeld.containsKey(_name); ++i) {
                    if(!market.containsKey(_name)) {
                        throw new IllegalArgumentException("Must provide price if not currently in market!");
                    }
                    _customer.moneyInDollars += market.get(_name).price;
                    int prevNum = _customer.stocksHeld.get(_name);
                    _customer.stocksHeld.put(_name, --prevNum);
                    ++market.get(_name).shares;
                }
            }
        }
        else
            throw new IllegalArgumentException("Customer must have stock to sell!");
    }
    
}
