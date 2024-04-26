/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mh.consultatelefonica.controller;

import com.mh.consultatelefonica.dto.PuestoDTO;
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
    
    @PostMapping("/puesto")
    public Puesto newPuesto(@RequestBody PuestoDTO newPuesto){
        return puestoService.savePuesto(newPuesto);
    }
    
    @GetMapping("/puestos")
    public List<PuestoDTO> getPuestos(){
        return puestoService.getPuestos();
    }
    
    @GetMapping("/puesto/{id}")
    public PuestoDTO getPuestoById(@PathVariable Long id){
        return puestoService.getPuestoById(id);
    }
    
    @PutMapping("/puesto/{id}")
    public Puesto updatePuesto(@RequestBody PuestoDTO newPuesto, @PathVariable Long id){
        return puestoService.updatePuesto(newPuesto, id);
    }
    
    @DeleteMapping("/puesto/{id}")
    public String deletePuesto(@PathVariable Long id){
        return puestoService.deletePuesto(id);
    }
}
