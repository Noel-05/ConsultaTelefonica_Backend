/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mh.consultatelefonica.repository;

import com.mh.consultatelefonica.model.EmpleadoTelefono;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author noel.renderos
 */
@Repository
public interface EmpleadoTelefonoRepository extends JpaRepository<EmpleadoTelefono, Long>{
    
    @Query("SELECT et FROM EmpleadoTelefono et INNER JOIN et.empleado e WHERE e.id = :empleadoId")
    public List<EmpleadoTelefono> getTelefonosByEmpleadoId(Long empleadoId);
}
