/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springbootajax.restfull.consumingajax.repo;

import com.springbootajax.restfull.consumingajax.entity.TransaksiDetil;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author java-spring
 */
public interface RepoTransaksiDetil extends JpaRepository<TransaksiDetil, String>{
    
}
