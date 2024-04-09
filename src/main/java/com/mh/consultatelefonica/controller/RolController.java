/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mh.consultatelefonica.controller;

import com.mh.consultatelefonica.model.Rol;
import com.mh.consultatelefonica.service.RolService;
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
public class RolController {
    
    @Autowired
    private RolService rolService;
    
    @PostMapping("/rol")
    public Rol newRol(@RequestBody Rol newRol){
        return rolService.saveRol(newRol);
    }
    
    @GetMapping("/roles")
    public List<Rol> getRoles(){
        return rolService.getRoles();
    }
    
    @GetMapping("/rol/{id}")
    public Rol getRolById(@PathVariable Long id){
        return rolService.getRolById(id);
    }
    
    @PutMapping("/rol/{id}")
    public Rol updateRol(@RequestBody Rol newRol, @PathVariable Long id){
        return rolService.updateRol(newRol, id);
    }
    
    @DeleteMapping("/rol/{id}")
    public String deleteRol(@PathVariable Long id){
        return rolService.deleteRol(id);
    }
}
