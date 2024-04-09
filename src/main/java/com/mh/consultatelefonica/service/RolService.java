/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mh.consultatelefonica.service;

import com.mh.consultatelefonica.exception.RolNotFoundException;
import com.mh.consultatelefonica.model.Rol;
import com.mh.consultatelefonica.repository.RolRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author noel.renderos
 */
@Service
public class RolService {
    
    @Autowired
    private RolRepository rolRepository;
    
    public Rol saveRol(Rol newRol){
        return rolRepository.save(newRol);
    }
    
    public List<Rol> getRoles(){
        return rolRepository.findAll();
    }
    
    public Rol getRolById(Long id){
        return rolRepository.findById(id)
                .orElseThrow(() -> new RolNotFoundException(id));
    }
    
    public Rol updateRol(Rol newRol, Long id){
        return rolRepository.findById(id)
                .map(rol -> {
                    rol.setCode(newRol.getCode());
                    rol.setName(newRol.getName());
                    
                    return rolRepository.save(rol);
                })
                .orElseThrow(() -> new RolNotFoundException(id));
    }
    
    public String deleteRol(Long id){
        if(!rolRepository.existsById(id)){
            throw new RolNotFoundException(id);
        }
        rolRepository.deleteById(id);
        
        return "Rol with id " + id + " has been succesfully deleted.";
    }
    
    /*Map<String, Map> deleteRol(Long id){
        if(!rolRepository.existsById(id)){
            throw new RolNotFoundException(id);
        }
        rolRepository.deleteById(id);
        
        Map<String, String> successMap = new HashMap<>();
        successMap.put("success", "User with id " + id + " has been deleted succesfully.");
        
        Map<String, Map> prueba = new HashMap<>();
        prueba.put("prueba", successMap);
        
        return prueba;
    }*/
}
