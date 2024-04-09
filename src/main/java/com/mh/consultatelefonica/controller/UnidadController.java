/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mh.consultatelefonica.controller;

import com.mh.consultatelefonica.model.Unidad;
import com.mh.consultatelefonica.service.UnidadService;
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
    private UnidadService unidadService;
    
    @PostMapping("/unidad/{dependenciaId}")
    public Unidad newUnidad(@RequestBody Unidad newUnidad, @PathVariable Long dependenciaId){
        return unidadService.saveUnidad(newUnidad, dependenciaId);
    }
    
    @GetMapping("/unidades")
    public List<Unidad> getUnidades(){
        return unidadService.getUnidades();
    }
    
    @GetMapping("/unidad/{id}")
    public Unidad getUnidadById(@PathVariable Long id){
        return unidadService.getUnidadById(id);
    }
    
    @PutMapping("/unidad/{dependenciaId}/{id}")
    public Unidad updateUnidad(@RequestBody Unidad newUnidad, @PathVariable("dependenciaId") Long dependenciaId, @PathVariable("id") Long id){
        return unidadService.updateUnidad(newUnidad, dependenciaId, id);
    }
    
    @DeleteMapping("/unidad/{id}")
    public String deleteUnidad(@PathVariable Long id){
        return unidadService.deleteUnidad(id);
    }
}
