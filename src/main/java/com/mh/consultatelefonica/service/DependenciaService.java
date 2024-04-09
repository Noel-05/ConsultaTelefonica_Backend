/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mh.consultatelefonica.service;

import com.mh.consultatelefonica.exception.DependenciaNotFoundException;
import com.mh.consultatelefonica.model.Dependencia;
import com.mh.consultatelefonica.repository.DependenciaRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author noel.renderos
 */
@Service
public class DependenciaService {
    
    @Autowired
    private DependenciaRepository dependenciaRepository;
    
    public Dependencia saveDependencia(Dependencia newDependencia){
        return dependenciaRepository.save(newDependencia);
    }
    
    public List<Dependencia> getDependencias(){
        return dependenciaRepository.findAll();
    }
    
    public Dependencia getDependenciaById(Long id){
        return dependenciaRepository.findById(id)
                .orElseThrow(() -> new DependenciaNotFoundException(id));
    }
    
    public Dependencia updateDependencia(Dependencia newDependencia, @PathVariable Long id){
        return dependenciaRepository.findById(id)
                .map(dependencia -> {
                    dependencia.setCode(newDependencia.getCode());
                    dependencia.setName(newDependencia.getName());
                    dependencia.setAddress(newDependencia.getAddress());
                    
                    return dependenciaRepository.save(dependencia);
                })
                .orElseThrow(() -> new DependenciaNotFoundException(id));
    }
    
    public String deleteDependencia(Long id){
        if(!dependenciaRepository.existsById(id)){
            throw new DependenciaNotFoundException(id);
        }
        dependenciaRepository.deleteById(id);
        
        return "Dependencia with id " + id + " has been succesfully deleted.";
    }
}
