/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mh.consultatelefonica.controller;

import com.mh.consultatelefonica.model.TipoTelefono;
import com.mh.consultatelefonica.service.TipoTelefonoService;
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
    private TipoTelefonoService tipoTelefonoService;
    
    @PostMapping("/tipoTelefono")
    public TipoTelefono newTipoTelefono(@RequestBody TipoTelefono newTipoTelefono){
        return tipoTelefonoService.saveTipoTelefono(newTipoTelefono);
    }
    
    @GetMapping("/tipoTelefonos")
    public List<TipoTelefono> getTipoTelefonos(){
        return tipoTelefonoService.getTipoTelefonos();
    }
    
    @GetMapping("/tipoTelefono/{id}")
    public TipoTelefono getTipoTelefonoById(@PathVariable Long id){
        return tipoTelefonoService.getTipoTelefonoById(id);
    }
    
    @PutMapping("/tipoTelefono/{id}")
    public TipoTelefono updateTipoTelefono(@RequestBody TipoTelefono newTipoTelefono, @PathVariable Long id){
        return tipoTelefonoService.updateTipoTelefono(newTipoTelefono, id);
    }
    
    @DeleteMapping("/tipoTelefono/{id}")
    public String deleteTipoTelefono(@PathVariable Long id){
        return tipoTelefonoService.deleteTipoTelefono(id);
    }
}
