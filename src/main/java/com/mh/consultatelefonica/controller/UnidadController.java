/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mh.consultatelefonica.controller;

import com.mh.consultatelefonica.dto.UnidadDTO;
import com.mh.consultatelefonica.model.Unidad;
import com.mh.consultatelefonica.service.UnidadService;
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
public class UnidadController {
    
    @Autowired
    private UnidadService unidadService;
    
    @PostMapping("/unidad")
    public Unidad newUnidad(@RequestBody UnidadDTO newUnidad){
        return unidadService.saveUnidad(newUnidad);
    }
    
    @GetMapping("/unidades")
    public List<UnidadDTO> getUnidades(){
        return unidadService.getUnidades();
    }
    
    @GetMapping("/unidad/{id}")
    public UnidadDTO getUnidadById(@PathVariable Long id){
        return unidadService.getUnidadById(id);
    }
    
    @PutMapping("/unidad/{id}")
    public Unidad updateUnidad(@RequestBody UnidadDTO newUnidad, @PathVariable Long id){
        return unidadService.updateUnidad(newUnidad, id);
    }
    
    @DeleteMapping("/unidad/{id}")
    public String deleteUnidad(@PathVariable Long id){
        return unidadService.deleteUnidad(id);
    }
    
    @GetMapping("/unidad/dependencia/{dependenciaId}")
    public List<UnidadDTO> getUnidadByDependencia(@PathVariable Long dependenciaId){
        return unidadService.getUnidadByDependencia(dependenciaId);
    }
}
