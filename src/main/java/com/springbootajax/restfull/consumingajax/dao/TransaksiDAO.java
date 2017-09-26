/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springbootajax.restfull.consumingajax.dao;

import com.springbootajax.restfull.consumingajax.entity.Transaksi;
import java.util.List;

/**
 *
 * @author java-spring
 */
public interface TransaksiDAO {
    
    void insertTransaksi(Transaksi transaksi);
    
    List<Transaksi> findAllTransaksi();
}
