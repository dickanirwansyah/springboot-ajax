/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springbootajax.restfull.consumingajax.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author java-spring
 */
@Entity
@Table(name = "kategori", 
        catalog = "springbootajax")
public class Kategori implements Serializable{
    
    @Id 
    @Column(name = "idkategori", nullable = false, unique = true)
    @GeneratedValue(generator = "uuid") 
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String idkategori;
    
    @Column(name = "kode", nullable = false, unique = true)
    private String kode;
    
    @Column(name = "nama", nullable = false)
    private String nama;
    
    @Column(name = "active", nullable = false)
    private boolean active;
    
    @JsonIgnore
    @OneToMany(mappedBy = "kategori")
    private List<Product> products = new ArrayList<Product>();
    
    public Kategori(){
        this.kode = "KTGR" + UUID.randomUUID()
                .toString().substring(26)
                .toUpperCase();
    }
    
    public Kategori(String idkategori, String kode, String nama, boolean active, 
            List<Product> products){
        this.idkategori=idkategori;
        this.nama=nama;
        this.kode=kode;
        this.active=active;
        this.products=products;
    }
    
    public String getIdkategori(){
        return idkategori;
    }
    
    public void setIdkategori(String idkategori){
        this.idkategori=idkategori;
    }
    
    public String getKode(){
        return kode;
    }
    
    public void setKode(String kode){
        this.kode=kode;
    }
    
    public String getNama(){
        return nama;
    }
    
    public void setNama(String nama){
        this.nama=nama;
    }
    
    public boolean getActive(){
        return active;
    }
    
    public void setActive(boolean active){
        this.active=active;
    }
    
    public List<Product> getProducts(){
        return products;
    }
    
    public void setProducts(List<Product>products){
        this.products=products;
    }
    
    public boolean addProduct(Product product){
       return products.add(product);
    }
}
