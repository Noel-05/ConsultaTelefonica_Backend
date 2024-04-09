/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mh.consultatelefonica.service;

import com.mh.consultatelefonica.exception.UnidadNotFoundException;
import com.mh.consultatelefonica.model.Dependencia;
import com.mh.consultatelefonica.model.Unidad;
import com.mh.consultatelefonica.repository.UnidadRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author noel.renderos
 */
@Service
public class UnidadService {
    
    @Autowired
    private DependenciaService dependenciaService;
    
    @Autowired
    private UnidadRepository unidadRepository;
    
    public Unidad saveUnidad(Unidad newUnidad, Long dependenciaId){
        Dependencia dependencia = dependenciaService.getDependenciaById(dependenciaId);
        newUnidad.setDependencia(dependencia);
        
        return unidadRepository.save(newUnidad);
    }
    
    public List<Unidad> getUnidades(){
        return unidadRepository.findAll();
    }
    
    public Unidad getUnidadById(Long id){
        return unidadRepository.findById(id)
                .orElseThrow(() -> new UnidadNotFoundException(id));
    }
    
    public Unidad updateUnidad(Unidad newUnidad, Long dependenciaId, Long id){
        Dependencia dependencia = dependenciaService.getDependenciaById(dependenciaId);
        
        return unidadRepository.findById(id)
                .map(unidad -> {
                    unidad.setCode(newUnidad.getCode());
                    unidad.setName(newUnidad.getName());
                    unidad.setAddress(newUnidad.getAddress());
                    unidad.setDependencia(dependencia);
                    
                    return unidadRepository.save(unidad);
                })
                .orElseThrow(() -> new UnidadNotFoundException(id));
    }
    
    public String deleteUnidad(Long id){
        if(!unidadRepository.existsById(id)){
            throw new UnidadNotFoundException(id);
        }
        unidadRepository.deleteById(id);
        
        return "Unidad with id " + id + " has been succesfully deleted";
    }
}
