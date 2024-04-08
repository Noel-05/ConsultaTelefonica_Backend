/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mh.consultatelefonica.controller;

import com.mh.consultatelefonica.exception.TipoTelefonoNotFoundException;
import com.mh.consultatelefonica.model.TipoTelefono;
import com.mh.consultatelefonica.repository.TipoTelefonoRepository;
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
public class TipoTelefonoController {
    
    @Autowired
    private TipoTelefonoRepository tipoTelefonoRepository;
    
    @PostMapping("/tipoTelefono")
    TipoTelefono newTipoTelefono(@RequestBody TipoTelefono newTipoTelefono){
        return tipoTelefonoRepository.save(newTipoTelefono);
    }
    
    @GetMapping("/tipoTelefonos")
    List<TipoTelefono> getTipoTelefonos(){
        return tipoTelefonoRepository.findAll();
    }
    
    @GetMapping("/tipoTelefono/{id}")
    TipoTelefono getTipoTelefonoById(@PathVariable Long id){
        return tipoTelefonoRepository.findById(id)
                .orElseThrow(() -> new TipoTelefonoNotFoundException(id));
    }
    
    @PutMapping("/tipoTelefono/{id}")
    TipoTelefono updateTipoTelefono(@RequestBody TipoTelefono newTipoTelefono, @PathVariable Long id){
        return tipoTelefonoRepository.findById(id)
                .map(tipoTelefono -> {
                    tipoTelefono.setName(newTipoTelefono.getName());
                    
                    return tipoTelefonoRepository.save(tipoTelefono);
                })
                .orElseThrow(() -> new TipoTelefonoNotFoundException(id));
    }
    
    @DeleteMapping("/tipoTelefono/{id}")
    String deleteTipoTelefono(@PathVariable Long id){
        if(!tipoTelefonoRepository.existsById(id)){
            throw new TipoTelefonoNotFoundException(id);
        }
        tipoTelefonoRepository.deleteById(id);
        
        return "Tipo Telefono with id " + id + " has been succesfully deleted";
    }
}
