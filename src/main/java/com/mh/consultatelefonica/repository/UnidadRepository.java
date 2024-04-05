/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mh.consultatelefonica.repository;

import com.mh.consultatelefonica.model.Unidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author noel.renderos
 */
@Repository
public interface UnidadRepository extends JpaRepository<Unidad, Long>{
    
    @Query("SELECT u FROM Unidad u JOIN FETCH u.dependencia WHERE u.id = ?1")
    Unidad findWithDependencia(Long id);
}
