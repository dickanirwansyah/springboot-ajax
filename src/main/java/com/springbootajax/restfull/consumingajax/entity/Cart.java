/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springbootajax.restfull.consumingajax.entity;

/**
 *
 * @author java-spring
 */
public class Cart {
    
    private Product product;
    
    private int jumlah;
    
    
    public Cart(){
        super();
    }
    
    public Cart(Product product, int jumlah){
        super();
        this.product=product;
        this.jumlah=jumlah;
    }
    
    public Product getProduct(){
        return product;
    }
    
    public void setProduct(Product product){
        this.product=product;
    }
    
    public int getJumlah(){
        return jumlah;
    }
    
    public void setJumlah(int jumlah){
        this.jumlah=jumlah;
    }
}
