/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springbootajax.restfull.consumingajax.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author java-spring
 */
@Embeddable
public class TransaksiDetilId implements Serializable{
    
    @Column(name = "idtransaksi", nullable = false)
    private String idtransaksi;
    
    @Column(name = "idproduct", nullable = false)
    private String idproduct;
    
    public TransaksiDetilId(){
        
    }
    
    public TransaksiDetilId(String idtransaksi, String idproduct){
        this.idproduct=idproduct;
        this.idtransaksi=idtransaksi;
    }
    
    public String getIdtransaksi(){
        return idtransaksi;
    }
    
    public void setIdtransaksi(String idtransaksi){
        this.idtransaksi=idtransaksi;
    }
    
    public String getIdroduct(){
        return idproduct;
    }
    
    public void setIdProduct(String idproduct){
        this.idproduct=idproduct;
    }
}
