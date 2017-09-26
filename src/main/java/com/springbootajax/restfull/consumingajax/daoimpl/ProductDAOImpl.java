/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springbootajax.restfull.consumingajax.daoimpl;

import com.springbootajax.restfull.consumingajax.dao.ProductDAO;
import com.springbootajax.restfull.consumingajax.entity.Product;
import com.springbootajax.restfull.consumingajax.repo.RepoProduct;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author java-spring
 */
@Service("productDAO")
@Transactional
public class ProductDAOImpl implements ProductDAO{
    
    @Autowired
    private RepoProduct repoProduct;

    @Override
    public List<Product> findAllProduct() {
     return repoProduct.findAll();
    }

    @Override
    public Product findByIdProduct(String idproduct) {
      return repoProduct.findOne(idproduct);
    }

    @Override
    public void insertUpdateProduct(Product product) {
      repoProduct.save(product);
    }

    @Override
    public void hapusProduct(Product product) {
      repoProduct.delete(product);
    }
    
    
}
