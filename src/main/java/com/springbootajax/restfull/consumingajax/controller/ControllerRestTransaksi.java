/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springbootajax.restfull.consumingajax.controller;

import com.springbootajax.restfull.consumingajax.dao.ProductDAO;
import com.springbootajax.restfull.consumingajax.dao.TransaksiDAO;
import com.springbootajax.restfull.consumingajax.dao.TransaksiDetilDAO;
import com.springbootajax.restfull.consumingajax.entity.Cart;
import com.springbootajax.restfull.consumingajax.entity.Product;
import com.springbootajax.restfull.consumingajax.entity.Transaksi;
import com.springbootajax.restfull.consumingajax.entity.TransaksiDetil;
import com.springbootajax.restfull.consumingajax.entity.TransaksiDetilId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author java-spring
 */
@RestController
@RequestMapping(value = "/api")
public class ControllerRestTransaksi {
    
    private static Logger LOGGER = 
            LoggerFactory.getLogger(ControllerRestTransaksi.class);
    
    @Autowired
    private TransaksiDAO transaksiDAO;
    
    @Autowired
    private TransaksiDetilDAO transaksiDetilDAO;
    
    @Autowired
    private ProductDAO productDAO;
    
    @RequestMapping(value = "/transaksiproducts", method = RequestMethod.GET)
    public ResponseEntity<List<Product>> listproduct(){
        LOGGER.info("Menampilkan transaksi list product");
        
        List<Product> listproducts=productDAO.findAllProduct();
        
        if(listproducts.isEmpty()){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
            return new ResponseEntity<List<Product>>(listproducts, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/insertTransaksi", method = RequestMethod.POST)
    public ResponseEntity insertTransaksi(HttpSession session){
        
        List<Cart> listcart = (List<Cart>) session.getAttribute("getcart");
        
        Transaksi transaksi = new Transaksi();
        transaksi.setTanggal(new Date());
        transaksiDAO.insertTransaksi(transaksi);
        
        for(int i=0; i<listcart.size(); i++){
            TransaksiDetil detil = new TransaksiDetil();
            detil.setTransaksiDetilId(new TransaksiDetilId(transaksi.getIdtransaksi(),
            listcart.get(i).getProduct().getIdproduct()));
            detil.setJumlah(listcart.get(i).getJumlah());
            transaksiDetilDAO.insertTransaksiDetil(detil);
        }
        session.removeAttribute("getcart");
        return new ResponseEntity(HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "/listtransaksi", method = RequestMethod.GET)
    public ResponseEntity<List<Transaksi>> findAllTransaksi(){
        
        List<Transaksi> listtransaksi = transaksiDAO.findAllTransaksi();
        
        if(listtransaksi.isEmpty()){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
            return new ResponseEntity<List<Transaksi>>(listtransaksi,HttpStatus.OK);
    }
    
    
    
   
//    @RequestMapping(value = "/insertTransaksi", method = RequestMethod.POST)
//    @ResponseBody
//    public ResponseEntity insertTransaksi(){
//        
//        List<TransaksiDetil> listdetils=new ArrayList<TransaksiDetil>();
//        
//        Transaksi transaksi=new Transaksi();
//        
//        transaksi.setTanggal(new Date());
//        
//        transaksiDAO.insertTransaksi(transaksi);
//        
//        for(int i=0; i<listdetils.size(); i++){
//            TransaksiDetil detil=new TransaksiDetil();
//            detil.setTransaksiDetilId(new TransaksiDetilId(transaksi.getIdtransaksi(),
//            listdetils.get(i).getProduct().getIdproduct()));
//         detil.setJumlah(listdetils.get(i).getJumlah());
//         transaksiDetilDAO.insertTransaksiDetil(detil);
//        }
//        return new ResponseEntity(HttpStatus.CREATED);
//    }
}
