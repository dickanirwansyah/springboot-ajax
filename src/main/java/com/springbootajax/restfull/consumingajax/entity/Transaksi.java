/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springbootajax.restfull.consumingajax.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author java-spring
 */
@Entity
@Table(name = "transaksi", 
        catalog = "springbootajax")
public class Transaksi implements Serializable{
    
    @Id @Column(name = "idtransaksi", unique = true, nullable = false)
    @GeneratedValue(generator = "uuid") @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String idtransaksi;
    
    @Column(name = "tanggal", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date tanggal;
    
    @JsonManagedReference
    @OneToMany(mappedBy = "transaksi")
    private Set<TransaksiDetil> transaksiDetils = new HashSet<TransaksiDetil>();
    
    public Transaksi(){
        
    }
    
    public Transaksi(String idtransaksi, Date tanggal){
        this.idtransaksi=idtransaksi;
        this.tanggal=tanggal;
    }
    
    public Transaksi(String idtransaksi, Date tanggal, Set<TransaksiDetil>transaksiDetils){
        this.idtransaksi=idtransaksi;
        this.tanggal=tanggal;
        this.transaksiDetils=transaksiDetils;
    }
    
    public String getIdtransaksi(){
        return idtransaksi;
    }
    
    public void setIdtransaksi(String idtransaksi){
        this.idtransaksi=idtransaksi;
    }
    
    public Date getTanggal(){
        return tanggal;
    }
    
    public void setTanggal(Date tanggal){
        this.tanggal=tanggal;
    }
    
    public Set<TransaksiDetil> getTransaksidetils(){
        return transaksiDetils;
    }
    
    public void setTransaksidetils(Set<TransaksiDetil> transaksiDetils){
        this.transaksiDetils=transaksiDetils;
    }
}
