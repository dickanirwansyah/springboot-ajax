/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springbootajax.restfull.consumingajax.dao;

import com.springbootajax.restfull.consumingajax.entity.Product;
import java.util.List;

/**
 *
 * @author java-spring
 */
public interface ProductDAO {
    
    List<Product> findAllProduct();
    
    Product findByIdProduct(String idproduct);
    
    void insertUpdateProduct(Product product);
    
    void hapusProduct(Product product);
}
