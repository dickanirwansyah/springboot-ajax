/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springbootajax.restfull.consumingajax.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author java-spring
 */
@Entity
@Table(name = "product",
        catalog = "springbootajax")
public class Product implements Serializable{
 
    @Id @Column(name = "idproduct", nullable = false, unique = true)
    @GeneratedValue(generator = "uuid") @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String idproduct;
    
    @Column(name = "kode", nullable = false, unique = true)
    private String kode;
    
    @Column(name = "nama", nullable = false)
    private String nama;
    
    @Column(name = "active", nullable = false)
    private boolean active;
    
    @Column(name = "harga", nullable = false)
    private double harga;
    
    @Column(name = "jumlah", nullable = false)
    private int jumlah;
    
    @Column(name = "keterangan", nullable = false)
    private String keterangan;
    
    @ManyToOne
    @JoinColumn(name = "idkategori", nullable = false)
    private Kategori kategori;
    
    @JsonManagedReference
    @OneToMany(mappedBy = "product")
    private Set<TransaksiDetil> transaksiDetils = new HashSet<TransaksiDetil>(0);
    
    public Product(){
        this.kode = "PRD"+UUID.randomUUID().toString().substring(26).toUpperCase();
    }
    
    public Product(Kategori kategori, String idproduct, String kode, String nama, boolean active, double harga, int jumlah, String keterangan) {
      this.kategori=kategori;
      this.idproduct=idproduct;
      this.kode=kode;
      this.nama=nama;
      this.active=active;
      this.harga=harga;
      this.jumlah=jumlah;
      this.keterangan=keterangan;
    }

   
    public Set<TransaksiDetil> getTransaksidetils(){
        return transaksiDetils;
    }
    
    public void setTransaksidetils(Set<TransaksiDetil> transaksiDetils){
        this.transaksiDetils=transaksiDetils;
    }
    
    public String getIdproduct() {
        return idproduct;
    }

    public void setIdproduct(String idproduct) {
        this.idproduct = idproduct;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public Kategori getKategori() {
        return kategori;
    }

    public void setKategori(Kategori kategori) {
        this.kategori = kategori;
    }
    
    
}
