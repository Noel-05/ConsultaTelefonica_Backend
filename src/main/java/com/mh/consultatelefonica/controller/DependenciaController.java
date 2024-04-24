/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mh.consultatelefonica.controller;

import com.mh.consultatelefonica.model.Dependencia;
import com.mh.consultatelefonica.service.DependenciaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin("http://localhost:3000")
public class DependenciaController {
    
    @Autowired
    private DependenciaService dependenciaService;
    
    @PostMapping("/dependencia")
    public Dependencia newDependencia(@RequestBody Dependencia newDependencia){
        return dependenciaService.saveDependencia(newDependencia);
    }
    
    @GetMapping("/dependencias")
    public List<Dependencia> getDependencias(){
        return dependenciaService.getDependencias();
    }
    
    @GetMapping("/dependencia/{id}")
    public Dependencia getDependenciaById(@PathVariable Long id){
        return dependenciaService.getDependenciaById(id);
    }
    
    @PutMapping("/dependencia/{id}")
    public Dependencia updateDependencia(@RequestBody Dependencia newDependencia, @PathVariable Long id){
        return dependenciaService.updateDependencia(newDependencia, id);
    }
    
    @DeleteMapping("/dependencia/{id}")
    public String deleteDependencia(@PathVariable Long id){
        return dependenciaService.deleteDependencia(id);
    }
}
