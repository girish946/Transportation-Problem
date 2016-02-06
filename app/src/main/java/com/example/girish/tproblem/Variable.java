package com.example.girish.tproblem;/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Formatter;

/**
 *
 * @author amjad
 */
public class Variable {  
        
    private int stock;
    private int required;
    private double value;
    
    public Variable(){
        this.stock = 0;
        this.required = 0;
    }
    
    public Variable(int stock, int required){
        this.stock = stock;
        this.required = required;
    }

    /**
     * @return the stock
     */
    public int getStock() {
        return stock;
    }

    /**
     * @param stock the stock to set
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * @return the required
     */
    public int getRequired() {
        return required;
    }

    /**
     * @param required the required to set
     */
    public void setRequired(int required) {
        this.required = required;
    }

    /**
     * @return the value
     */
    public double getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        Formatter f = new Formatter();
        f.format("%d %d %d", this.stock, this.required, (int)this.value);
        return f.toString();
    }
    
    

    
}

