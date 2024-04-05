/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mh.consultatelefonica.controller;

import com.mh.consultatelefonica.exception.DependenciaNotFoundException;
import com.mh.consultatelefonica.exception.UnidadNotFoundException;
import com.mh.consultatelefonica.model.Dependencia;
import com.mh.consultatelefonica.model.Unidad;
import com.mh.consultatelefonica.repository.DependenciaRepository;
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
public class UnidadController {
    
    @Autowired
    private UnidadRepository unidadRepository;
    
    @Autowired
    private DependenciaRepository dependenciaRepository;
    
    @PostMapping("/unidad/{dependenciaId}")
    Unidad newUnidad(@RequestBody Unidad newUnidad, @PathVariable Long dependenciaId){
        Dependencia dependencia = dependenciaRepository.findById(dependenciaId)
                .orElseThrow(() -> new DependenciaNotFoundException(dependenciaId));
        newUnidad.setDependencia(dependencia);
        
        return unidadRepository.save(newUnidad);
    }
    
    @GetMapping("/unidades")
    List<Unidad> getUnidades(){
        return unidadRepository.findAll();
    }
    
    @GetMapping("/unidad/{id}")
    Unidad getUnidad(@PathVariable Long id){
        return unidadRepository.findById(id)
                .orElseThrow(() -> new UnidadNotFoundException(id));
    }
    
    @PutMapping("/unidad/{dependenciaId}/{id}")
    Unidad updateUnidad(@RequestBody Unidad newUnidad, @PathVariable("dependenciaId") Long dependenciaId, @PathVariable("id") Long id){
        Dependencia dependencia = dependenciaRepository.findById(dependenciaId)
                .orElseThrow(() -> new DependenciaNotFoundException(dependenciaId));
        
        return unidadRepository.findById(id)
                .map(unidad -> {
                    unidad.setCode(newUnidad.getCode());
                    unidad.setName(newUnidad.getName());
                    unidad.setAddress(newUnidad.getAddress());
                    unidad.setDependencia(dependencia);
                    
                    return unidadRepository.save(unidad);
                })
                .orElseThrow(() -> new UnidadNotFoundException(id));
    }
    
    @DeleteMapping("/unidad/{id}")
    String deleteUnidad(@PathVariable Long id){
        if(!unidadRepository.existsById(id)){
            throw new UnidadNotFoundException(id);
        }
        unidadRepository.deleteById(id);
        
        return "Unidad with id " + id + " has been succesfully deleted";
    }
}
