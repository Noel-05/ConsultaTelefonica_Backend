/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mh.consultatelefonica.service;

import com.mh.consultatelefonica.dto.UnidadDTO;
import com.mh.consultatelefonica.exception.PuestoNotFoundException;
import com.mh.consultatelefonica.mapper.UnidadMapper;
import com.mh.consultatelefonica.model.Puesto;
import com.mh.consultatelefonica.model.Rol;
import com.mh.consultatelefonica.model.Unidad;
import com.mh.consultatelefonica.repository.PuestoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author noel.renderos
 */
@Service
public class PuestoService {
    
    @Autowired
    private RolService rolService;
    
    @Autowired
    private UnidadService unidadService;
    
    @Autowired
    private UnidadMapper unidadMapper;
    
    @Autowired 
    private PuestoRepository puestoRepository;
    
    public Puesto savePuesto(Puesto newPuesto, Long rolId, Long unidadId){
        Rol findRol = rolService.getRolById(rolId);
        UnidadDTO unidadDto = unidadService.getUnidadById(unidadId);
        Unidad findUnidad = unidadMapper.unidadDtoToEntity(unidadDto);
        newPuesto.setRol(findRol);
        newPuesto.setUnidad(findUnidad);
        
        return puestoRepository.save(newPuesto);
    }
    
    public List<Puesto> getPuestos(){
        return puestoRepository.findAll();
    }
    
    public Puesto getPuestoById(Long id){
        return puestoRepository.findById(id)
                .orElseThrow(() -> new PuestoNotFoundException(id));
    }
    
    public Puesto updatePuesto(Puesto newPuesto, Long rolId, Long unidadId, Long id){
        Rol findRol = rolService.getRolById(rolId);
        UnidadDTO unidadDto = unidadService.getUnidadById(unidadId);
        Unidad findUnidad = unidadMapper.unidadDtoToEntity(unidadDto);
        
        return puestoRepository.findById(id)
                .map(puesto -> {
                    puesto.setName(newPuesto.getName());
                    puesto.setSalary(newPuesto.getSalary());
                    puesto.setRol(findRol);
                    puesto.setUnidad(findUnidad);
                    
                    return puestoRepository.save(puesto);
                })
                .orElseThrow(() -> new PuestoNotFoundException(id));
    }
    
    public String deletePuesto(Long id){
        if(!puestoRepository.existsById(id)){
            throw new PuestoNotFoundException(id);
        }
        puestoRepository.deleteById(id);
        
        return "Puesto with id " + id + " has been succesfully deleted";
    }
}
