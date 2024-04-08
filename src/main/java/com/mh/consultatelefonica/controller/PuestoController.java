/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mh.consultatelefonica.controller;

import com.mh.consultatelefonica.exception.PuestoNotFoundException;
import com.mh.consultatelefonica.exception.RolNotFoundException;
import com.mh.consultatelefonica.exception.UnidadNotFoundException;
import com.mh.consultatelefonica.model.Puesto;
import com.mh.consultatelefonica.model.Rol;
import com.mh.consultatelefonica.model.Unidad;
import com.mh.consultatelefonica.repository.PuestoRepository;
import com.mh.consultatelefonica.repository.RolRepository;
import com.mh.consultatelefonica.repository.UnidadRepository;
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
public class PuestoController {
    
    @Autowired
    private RolRepository rolRepository;
    
    @Autowired
    private UnidadRepository unidadRepository;
    
    @Autowired PuestoRepository puestoRepository;
    
    @PostMapping("/puesto/{rolId}/{unidadId}")
    Puesto newPuesto(@RequestBody Puesto newPuesto, @PathVariable("rolId") Long rolId, @PathVariable("unidadId") Long unidadId){
        Rol findRol = rolRepository.findById(rolId)
                .orElseThrow(() -> new RolNotFoundException(rolId));
        Unidad findUnidad = unidadRepository.findById(unidadId)
                .orElseThrow(() -> new UnidadNotFoundException(unidadId));
        newPuesto.setRol(findRol);
        newPuesto.setUnidad(findUnidad);
        
        return puestoRepository.save(newPuesto);
    }
    
    @GetMapping("/puestos")
    List<Puesto> getPuestos(){
        return puestoRepository.findAll();
    }
    
    @GetMapping("/puesto/{id}")
    Puesto getPuesto(@PathVariable Long id){
        return puestoRepository.findById(id)
                .orElseThrow(() -> new PuestoNotFoundException(id));
    }
    
    @PutMapping("/puesto/{rolId}/{unidadId}/{id}")
    Puesto updatePuesto(@RequestBody Puesto newPuesto, @PathVariable("rolId") Long rolId, @PathVariable("unidadId") Long unidadId, @PathVariable("id") Long id){
        Rol findRol = rolRepository.findById(rolId)
                .orElseThrow(() -> new RolNotFoundException(rolId));
        Unidad findUnidad = unidadRepository.findById(unidadId)
                .orElseThrow(() -> new UnidadNotFoundException(unidadId));
        
        return puestoRepository.findById(id)
                .map(puesto -> {
                    puesto.setName(newPuesto.getName());
                    puesto.setSalary(newPuesto.getSalary());
                    puesto.setRol(findRol);
                    puesto.setUnidad(findUnidad);
                    
                    return puestoRepository.save(puesto);
                })
                .orElseThrow(() -> new PuestoNotFoundException(id));
    }
    
    @DeleteMapping("/puesto/{id}")
    String deletePuesto(@PathVariable Long id){
        if(!puestoRepository.existsById(id)){
            throw new PuestoNotFoundException(id);
        }
        puestoRepository.deleteById(id);
        
        return "Puesto with id " + id + " has been succesfully deleted";
    }
}
