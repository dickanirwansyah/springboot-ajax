/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springbootajax.restfull.consumingajax.daoimpl;

import com.springbootajax.restfull.consumingajax.dao.TransaksiDAO;
import com.springbootajax.restfull.consumingajax.entity.Transaksi;
import com.springbootajax.restfull.consumingajax.repo.RepoTransaksi;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author java-spring
 */
@Service("transaksiDAO")
@Transactional
public class TransaksiDAOImpl implements TransaksiDAO{

    @Autowired
    private RepoTransaksi repoTransaksi;
    
    @Override
    public void insertTransaksi(Transaksi transaksi) {
      repoTransaksi.save(transaksi);
    }

    @Override
    public List<Transaksi> findAllTransaksi() {
      return repoTransaksi.findAll();
    }
    
}
