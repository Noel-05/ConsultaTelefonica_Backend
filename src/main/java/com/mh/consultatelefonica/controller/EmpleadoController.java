/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mh.consultatelefonica.controller;

import com.mh.consultatelefonica.exception.EmpleadoNotFoundException;
import com.mh.consultatelefonica.exception.PuestoNotFoundException;
import com.mh.consultatelefonica.model.Empleado;
import com.mh.consultatelefonica.model.Puesto;
import com.mh.consultatelefonica.repository.EmpleadoRepository;
import com.mh.consultatelefonica.repository.PuestoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author noel.renderos
 */
@RestController
public class EmpleadoController {
    
    @Autowired
    private PuestoRepository puestoRepository;
    
    @Autowired
    private EmpleadoRepository empleadoRepository;
    
    @PostMapping("/empleado/{puestoId}")
    Empleado newEmpleado(@RequestBody Empleado newEmpleado, @PathVariable Long puestoId){
        Puesto findPuesto = puestoRepository.findById(puestoId)
                .orElseThrow(() -> new PuestoNotFoundException(puestoId));
        newEmpleado.setPuesto(findPuesto);
        
        return empleadoRepository.save(newEmpleado);
    }
    
    @GetMapping("/empleados")
    List<Empleado> getEmpleados(){
        return empleadoRepository.findAll();
    }
    
    @GetMapping("/empleado/{id}")
    Empleado getEmpleadoById(@PathVariable Long id){
        return empleadoRepository.findById(id)
                .orElseThrow(() -> new EmpleadoNotFoundException(id));
    }
    
    @PutMapping("/empleado/{puestoId}/{id}")
    Empleado updateEmpleado(@RequestBody Empleado newEmpleado, @PathVariable("puestoId") Long puestoId, @PathVariable("id") Long id){
        Puesto findPuesto = puestoRepository.findById(puestoId)
                .orElseThrow(() -> new PuestoNotFoundException(puestoId));
        
        return empleadoRepository.findById(id)
                .map(empleado -> {
                    empleado.setCarnet(newEmpleado.getCarnet());
                    empleado.setDui(newEmpleado.getDui());
                    empleado.setFirst_name(newEmpleado.getFirst_name());
                    empleado.setLast_name(newEmpleado.getLast_name());
                    empleado.setBirth_date(newEmpleado.getBirth_date());
                    empleado.setStart_date(newEmpleado.getStart_date());
                    empleado.setPuesto(findPuesto);
                    
                    return empleadoRepository.save(empleado);
                })
                .orElseThrow(() -> new EmpleadoNotFoundException(id));
    }
    
    @DeleteMapping("/empleado/{id}")
    String deleteEmployee(@PathVariable Long id){
        if(!empleadoRepository.existsById(id)){
            throw new EmpleadoNotFoundException(id);
        }
        empleadoRepository.deleteById(id);
        
        return "Employee with id " + id + " has been succesfully deleted";
    }
}
