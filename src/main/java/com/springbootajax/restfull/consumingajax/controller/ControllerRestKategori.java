/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springbootajax.restfull.consumingajax.controller;

import com.springbootajax.restfull.consumingajax.dao.KategoriDAO;
import com.springbootajax.restfull.consumingajax.entity.Kategori;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author java-spring
 */
@RestController
@RequestMapping(value = "/api")
public class ControllerRestKategori {
    
    private static final Logger 
            LOGGER = LoggerFactory.getLogger(ControllerRestKategori.class);
    
    @Autowired
    private KategoriDAO kategoriDAO;
    
    @RequestMapping(value = "/insertkategori", method = RequestMethod.POST, 
            produces = "application/json" )
    public ResponseEntity insertKategori(@RequestBody Kategori kategori){
        LOGGER.info("Insert Kategori");
        
       if(kategori.getIdkategori() != null){
           kategori.setKode(kategori.getKode());
           kategoriDAO.insertAndUpdateKategori(kategori);
       }else{
           kategoriDAO.insertAndUpdateKategori(kategori);
       }
       return new ResponseEntity(HttpStatus.OK);
    }
    
    @RequestMapping(value = "/getkategori/{idkategori}", method = RequestMethod.GET)
    public ResponseEntity<Kategori>getByIdkategori(@PathVariable("idkategori")String idkategori){
        
        Kategori kategori = new Kategori();
        
        kategori = kategoriDAO.findOneByIdkategori(idkategori);
        
        if(kategori == null){
            LOGGER.info("kode kategori {} tidak ditemukan");
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Kategori>(kategori, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/kategoris", method = RequestMethod.GET)
    public ResponseEntity<List<Kategori>> findAllKategoris(){
        LOGGER.info("Menampilkan data kategori");
        
        List<Kategori> listkategori = kategoriDAO.findAllKategori();
        
        if(listkategori.isEmpty()){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
            return new ResponseEntity<List<Kategori>>(listkategori, HttpStatus.OK);
    }
}
