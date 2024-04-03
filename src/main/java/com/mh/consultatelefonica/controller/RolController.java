/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mh.consultatelefonica.controller;

import com.mh.consultatelefonica.exception.RolNotFoundException;
import com.mh.consultatelefonica.model.Rol;
import com.mh.consultatelefonica.repository.RolRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author noel.renderos
 */
@RestController
public class RolController {
    
    @Autowired
    private RolRepository rolRepository;
    
    @PostMapping("/rol")
    Rol newRol(@RequestBody Rol newRol){
        return rolRepository.save(newRol);
    }
    
    @GetMapping("/roles")
    List<Rol> getRoles(){
        return rolRepository.findAll();
    }
    
    @GetMapping("/rol/{id}")
    Rol getRol(@PathVariable Long id){
        return rolRepository.findById(id)
                .orElseThrow(() -> new RolNotFoundException(id));
    }
    
}
