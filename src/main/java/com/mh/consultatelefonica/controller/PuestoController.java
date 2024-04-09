/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mh.consultatelefonica.controller;

import com.mh.consultatelefonica.model.Puesto;
import com.mh.consultatelefonica.service.PuestoService;
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
    private PuestoService puestoService;
    
    @PostMapping("/puesto/{rolId}/{unidadId}")
    public Puesto newPuesto(@RequestBody Puesto newPuesto, @PathVariable("rolId") Long rolId, @PathVariable("unidadId") Long unidadId){
        return puestoService.savePuesto(newPuesto, rolId, unidadId);
    }
    
    @GetMapping("/puestos")
    public List<Puesto> getPuestos(){
        return puestoService.getPuestos();
    }
    
    @GetMapping("/puesto/{id}")
    public Puesto getPuestoById(@PathVariable Long id){
        return puestoService.getPuestoById(id);
    }
    
    @PutMapping("/puesto/{rolId}/{unidadId}/{id}")
    public Puesto updatePuesto(@RequestBody Puesto newPuesto, @PathVariable("rolId") Long rolId, @PathVariable("unidadId") Long unidadId, @PathVariable("id") Long id){
        return puestoService.updatePuesto(newPuesto, rolId, unidadId, id);
    }
    
    @DeleteMapping("/puesto/{id}")
    public String deletePuesto(@PathVariable Long id){
        return puestoService.deletePuesto(id);
    }
}
