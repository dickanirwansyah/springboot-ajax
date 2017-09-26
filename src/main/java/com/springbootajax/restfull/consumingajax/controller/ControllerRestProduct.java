/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springbootajax.restfull.consumingajax.controller;

import com.springbootajax.restfull.consumingajax.dao.KategoriDAO;
import com.springbootajax.restfull.consumingajax.dao.ProductDAO;
import com.springbootajax.restfull.consumingajax.entity.Kategori;
import com.springbootajax.restfull.consumingajax.entity.Product;
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
public class ControllerRestProduct {
    
    private static final Logger LOGGER = 
            LoggerFactory.getLogger(ControllerRestProduct.class);
    
    @Autowired
    private ProductDAO productDAO;
    
    @Autowired
    private KategoriDAO kategoriDAO;
    
    @RequestMapping(value = "/products")
    public ResponseEntity<List<Product>> findAllProduct(){
        List<Product> listproducts=productDAO.findAllProduct();
        
        if(listproducts.isEmpty()){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
            return new ResponseEntity<List<Product>>(listproducts, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/products/{idproduct}", method = RequestMethod.GET)
    public ResponseEntity<Product> findByIdproduct(@PathVariable("idproduct")String idproduct){
        
        LOGGER.info("menampilkan idproduct : {} "+idproduct);
        
        Product product = productDAO.findByIdProduct(idproduct);
        
        if(product == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
            return new ResponseEntity<Product>(product, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/hapusproduct/{idproduct}", method = RequestMethod.DELETE)
    public ResponseEntity hapusProductByIdproduct(@PathVariable("idproduct")String idproduct){
        
        LOGGER.info("hapus product berdasarkan idproduct {} "+idproduct);
        
        Product product=productDAO.findByIdProduct(idproduct);
        
        productDAO.hapusProduct(product);
        
        return new ResponseEntity(HttpStatus.OK);
    }
    
    @RequestMapping(value = "/insertproduct/{idkategori}", method = RequestMethod.POST)
    public ResponseEntity insertProduct(@PathVariable String idkategori, 
            @RequestBody Product product){
        
        LOGGER.info("Insert product");
        
        Kategori kategori = kategoriDAO.findOneByIdkategori(idkategori);
        
        if(product.getIdproduct() != null){
            Product pp = new Product(kategori, product.getIdproduct(), 
                    product.getKode(), 
                    product.getNama(), 
                    product.getActive(), 
                    product.getHarga(), 
                    product.getJumlah(),
                    product.getKeterangan());
            pp.setKode(pp.getKode());
            productDAO.insertUpdateProduct(pp);
            kategori.addProduct(pp);
            kategoriDAO.insertAndUpdateKategori(kategori);
        }else{
            Product pp = new Product(kategori, 
                    product.getIdproduct(), 
                    product.getKode(), 
                    product.getNama(), 
                    product.getActive(), 
                    product.getHarga(),
                    product.getJumlah(), 
                    product.getKeterangan());
            productDAO.insertUpdateProduct(pp);
            kategoriDAO.insertAndUpdateKategori(kategori);
        }
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
