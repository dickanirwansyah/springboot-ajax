/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springbootajax.restfull.consumingajax.controller;

import com.springbootajax.restfull.consumingajax.dao.ProductDAO;
import com.springbootajax.restfull.consumingajax.entity.Cart;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author java-spring
 */
@Controller
@RequestMapping(value = "/data")
public class ControllerPages {
    
    @Autowired
    private ProductDAO productDAO;
    
    @RequestMapping(value = "/kategoris", method = RequestMethod.GET)
    public ModelAndView getPageKategoris(){
        ModelAndView view = new ModelAndView();
        
        view.setViewName("kategori/index");
        
        return view;
    }
    
    //validasi stock product
    private int JikaProductSudahAda(String idproduct, HttpSession session){
        List<Cart> listcarts = (List<Cart>) session.getAttribute("getcart");
        for(int i=0; i<listcarts.size(); i++){
            if(listcarts.get(i).getProduct().getIdproduct().equals(idproduct)){
                return i;
            }
        }
        return -1;
    }
    
     //proses insert cart intotable
    @RequestMapping(value = "/insertProductToCart/{idproduct}", method = RequestMethod.GET)
    public String InsertProductToCart(@PathVariable("idproduct")String idproduct,
            HttpSession session){
        
        if(session.getAttribute("getcart")==null){
            List<Cart> listcart = new ArrayList<Cart>();
            listcart.add(new Cart(productDAO.findByIdProduct(idproduct), 1));
            session.setAttribute("getcart", listcart);
            
        }else{
            List<Cart> listcart = (List<Cart>) session.getAttribute("getcart");
            int index=JikaProductSudahAda(idproduct, session);
            if(index == -1){
                listcart.add(new Cart(productDAO.findByIdProduct(idproduct), 1));
            }else{
                
                int jumlah = listcart.get(index).getJumlah()+1;
                listcart.get(index).setJumlah(jumlah);
                session.setAttribute("getcart", listcart);
            }
            session.setAttribute("getcart", listcart);
        }
        return "redirect:/data/yourcart";
    }
    
    @RequestMapping(value = "/datatransaksi", method = RequestMethod.GET)
    public ModelAndView getPageListDataTransaksi(){
        ModelAndView view = new ModelAndView();
        
        view.setViewName("transaksi/datatransaksi");
        
        return view;
    }
    
    @RequestMapping(value = "/transaksi", method = RequestMethod.GET)
    public ModelAndView getPageTransaksi(){
        ModelAndView view =new ModelAndView();
        
        view.setViewName("transaksi/index");
        
        return view;
    }
    
    @RequestMapping(value = "/yourcart", method = RequestMethod.GET)
    public ModelAndView getPageCart(){
        ModelAndView view = new ModelAndView();
        view.setViewName("transaksi/cart");
        return view;
    }
    
    @RequestMapping(value = "/showproducts", method = RequestMethod.GET)
    public ModelAndView getPageListProductsTransaksi(){
        ModelAndView view = new ModelAndView();
        
        view.setViewName("transaksi/listproduct");
        
        return view;
    }
    
    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public ModelAndView getPageProduct(){
        ModelAndView view=new ModelAndView();
        
        view.setViewName("product/index");
        
        return view;
    }
}
