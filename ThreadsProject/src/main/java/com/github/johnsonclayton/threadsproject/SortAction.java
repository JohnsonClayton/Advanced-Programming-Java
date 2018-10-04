/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.johnsonclayton.threadsproject;

import java.util.List;

/**
 *
 * @author clayt
 */
public class SortAction implements Action {

    @Override
    public void actOn(List list) {
        list.sort(null);
    }
    
}
