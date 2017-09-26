/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springbootajax.restfull.consumingajax.repo;

import com.springbootajax.restfull.consumingajax.entity.Kategori;
import java.io.Serializable;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author java-spring
 */
public interface RepoKategori extends JpaRepository<Kategori, String>{
    
   
}
