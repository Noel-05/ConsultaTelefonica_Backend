/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mh.consultatelefonica.repository;

import com.mh.consultatelefonica.model.Empleado;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author noel.renderos
 */
@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long>{
    
    @Query("SELECT e FROM Empleado e WHERE e.first_name LIKE CONCAT('%', :firstname, '%')")
    public List<Empleado> filterEmpleadoFirstName(String firstname);
    
    @Query("SELECT e FROM Empleado e WHERE e.last_name LIKE CONCAT('%', :lastname, '%')")
    public List<Empleado> filterEmpleadoLastName(String lastname);
    
    @Query("SELECT e FROM Empleado e WHERE e.first_name LIKE CONCAT('%', :firstname, '%') AND e.last_name LIKE CONCAT('%', :lastname, '%')")
    public List<Empleado> filterEmpleadoFirstAndLastName(String firstname, String lastname);
    
    @Query("SELECT e FROM EmpleadoTelefono et INNER JOIN et.empleado e WHERE et.empleado.id = e.id")
    public List<Empleado> getEmpleadosConTelefonos();
}
