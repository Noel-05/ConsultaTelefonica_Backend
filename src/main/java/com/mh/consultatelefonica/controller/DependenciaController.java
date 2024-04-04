/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mh.consultatelefonica.controller;

import com.mh.consultatelefonica.exception.DependenciaNotFoundException;
import com.mh.consultatelefonica.model.Dependencia;
import com.mh.consultatelefonica.repository.DependenciaRepository;
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
public class DependenciaController {
    
    @Autowired
    private DependenciaRepository dependenciaRepository;
    
    @PostMapping("/dependencia")
    Dependencia newDependencia(@RequestBody Dependencia newDependencia){
        return dependenciaRepository.save(newDependencia);
    }
    
    @GetMapping("/dependencias")
    List<Dependencia> getDependencias(){
        return dependenciaRepository.findAll();
    }
    
    @GetMapping("/dependencia/{id}")
    Dependencia getDependenciaById(@PathVariable Long id){
        return dependenciaRepository.findById(id)
                .orElseThrow(() -> new DependenciaNotFoundException(id));
    }
    
    @PutMapping("/dependencia/{id}")
    Dependencia updateDependencia(@RequestBody Dependencia newDependencia, @PathVariable Long id){
        return dependenciaRepository.findById(id)
                .map(dependencia -> {
                    dependencia.setCode(newDependencia.getCode());
                    dependencia.setName(newDependencia.getName());
                    dependencia.setAddress(newDependencia.getAddress());
                    
                    return dependenciaRepository.save(dependencia);
                })
                .orElseThrow(() -> new DependenciaNotFoundException(id));
    }
    
    @DeleteMapping("/dependencia/{id}")
    String deleteDependencia(@PathVariable Long id){
        if(!dependenciaRepository.existsById(id)){
            throw new DependenciaNotFoundException(id);
        }
        dependenciaRepository.deleteById(id);
        
        return "Dependencia with id " + id + " has been succesfully deleted.";
    }
}
