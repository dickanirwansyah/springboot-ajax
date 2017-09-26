/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springbootajax.restfull.consumingajax.dao;

import com.springbootajax.restfull.consumingajax.entity.Kategori;
import java.util.List;

/**
 *
 * @author java-spring
 */
public interface KategoriDAO {
    
    List<Kategori> findAllKategori();
    
    Kategori findOneByIdkategori(String idkategori);
    
    void insertAndUpdateKategori(Kategori kategori);
}
