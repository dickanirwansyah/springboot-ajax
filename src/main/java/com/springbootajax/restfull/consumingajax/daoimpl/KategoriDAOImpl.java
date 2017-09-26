/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springbootajax.restfull.consumingajax.daoimpl;

import com.springbootajax.restfull.consumingajax.dao.KategoriDAO;
import com.springbootajax.restfull.consumingajax.entity.Kategori;
import com.springbootajax.restfull.consumingajax.repo.RepoKategori;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author java-spring
 */
@Service("kategoriDAO")
@Transactional
public class KategoriDAOImpl implements KategoriDAO{
    
    @Autowired
    private RepoKategori repoKategori;

    @Override
    public List<Kategori> findAllKategori() {
      return repoKategori.findAll();
    }

    @Override
    public Kategori findOneByIdkategori(String idkategori) {
      return repoKategori.findOne(idkategori);
    }

    @Override
    public void insertAndUpdateKategori(Kategori kategori) {
      repoKategori.save(kategori);
    }
    
}
