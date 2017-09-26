/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springbootajax.restfull.consumingajax.daoimpl;

import com.springbootajax.restfull.consumingajax.dao.TransaksiDetilDAO;
import com.springbootajax.restfull.consumingajax.entity.TransaksiDetil;
import com.springbootajax.restfull.consumingajax.repo.RepoTransaksiDetil;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author java-spring
 */
@Service("transaksiDetilDAO")
@Transactional
public class TransaksiDetilDAOImpl implements TransaksiDetilDAO{

    @Autowired
    private RepoTransaksiDetil repoTransaksiDetil;
    
    @Override
    public void insertTransaksiDetil(TransaksiDetil transaksiDetil) {
      repoTransaksiDetil.save(transaksiDetil);
    }
    
}
