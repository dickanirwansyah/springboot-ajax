/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springbootajax.restfull.consumingajax.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.io.Serializable;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author java-spring
 */
@Entity
@Table(name = "transaksi_detil", 
        catalog = "springbootajax")
public class TransaksiDetil implements Serializable{
    
    @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = "idtransaksi", column = @Column(name = "idtransaksi", nullable = false)),
        @AttributeOverride(name = "idproduct", column = @Column(name = "idproduct", nullable = false))
    })
    private TransaksiDetilId id;
    
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "idtransaksi", nullable = false, insertable = false, updatable = false)
    private Transaksi transaksi;
    
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "idproduct", nullable = false, insertable = false, updatable = false)
    private Product product;
    
    @Column(name = "jumlah", nullable = false)
    private int jumlah;
    
    public TransaksiDetil(){
        
    }
    
    public TransaksiDetil(TransaksiDetilId id, Transaksi transaksi, Product product, int jumlah){
        this.id=id;
        this.transaksi=transaksi;
        this.product=product;
        this.jumlah=jumlah;
    }
    
    public TransaksiDetilId getId(){
        return id;
    }
    
    public void setTransaksiDetilId(TransaksiDetilId id){
        this.id=id;
    }
    
    public Product getProduct(){
        return product;
    }
    
    public void setProduct(Product product){
        this.product=product;
    }
    
    public Transaksi getTransaksi(){
        return transaksi;
    }
    
    public void setTransaksi(Transaksi transaksi){
        this.transaksi=transaksi;
    }
    
    public int getJumlah(){
        return jumlah;
    }
    
    public void setJumlah(int jumlah){
        this.jumlah=jumlah;
    }
}
