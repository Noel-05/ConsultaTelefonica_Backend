/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mh.consultatelefonica.repository;

import com.mh.consultatelefonica.model.Puesto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author noel.renderos
 */
@Repository
public interface PuestoRepository extends JpaRepository<Puesto, Long>{
    
}
