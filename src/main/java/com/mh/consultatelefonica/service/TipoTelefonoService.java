/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mh.consultatelefonica.service;

import com.mh.consultatelefonica.exception.TipoTelefonoNotFoundException;
import com.mh.consultatelefonica.model.TipoTelefono;
import com.mh.consultatelefonica.repository.TipoTelefonoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author noel.renderos
 */
@Service
public class TipoTelefonoService {
    
    @Autowired
    private TipoTelefonoRepository tipoTelefonoRepository;
    
    public TipoTelefono saveTipoTelefono(TipoTelefono newTipoTelefono){
        return tipoTelefonoRepository.save(newTipoTelefono);
    }
    
    public List<TipoTelefono> getTipoTelefonos(){
        return tipoTelefonoRepository.findAll();
    }
    
    public TipoTelefono getTipoTelefonoById(Long id){
        return tipoTelefonoRepository.findById(id)
                .orElseThrow(() -> new TipoTelefonoNotFoundException(id));
    }
    
    public TipoTelefono updateTipoTelefono(TipoTelefono newTipoTelefono, Long id){
        return tipoTelefonoRepository.findById(id)
                .map(tipoTelefono -> {
                    tipoTelefono.setName(newTipoTelefono.getName());
                    
                    return tipoTelefonoRepository.save(tipoTelefono);
                })
                .orElseThrow(() -> new TipoTelefonoNotFoundException(id));
    }
    
    public String deleteTipoTelefono(Long id){
        if(!tipoTelefonoRepository.existsById(id)){
            throw new TipoTelefonoNotFoundException(id);
        }
        tipoTelefonoRepository.deleteById(id);
        
        return "Tipo Telefono with id " + id + " has been succesfully deleted";
    }
}
