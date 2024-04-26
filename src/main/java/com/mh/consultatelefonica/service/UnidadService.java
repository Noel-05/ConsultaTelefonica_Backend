/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mh.consultatelefonica.service;

import com.mh.consultatelefonica.dto.UnidadDTO;
import com.mh.consultatelefonica.exception.UnidadNotFoundException;
import com.mh.consultatelefonica.mapper.UnidadMapper;
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
    
    @Autowired
    private UnidadMapper unidadMapper;
    
    public Unidad saveUnidad(UnidadDTO unidadDto){
        Dependencia dependencia = dependenciaService.getDependenciaById(unidadDto.getDependencia_id());
        
        Unidad newUnidad = unidadMapper.unidadDtoToEntity(unidadDto);
        newUnidad.setDependencia(dependencia);
        
        return unidadRepository.save(newUnidad);
    }
    
    public List<UnidadDTO> getUnidades(){
        List<Unidad> unidadesList = unidadRepository.findAll();
        
        return unidadMapper.unidadToDtoList(unidadesList);
    }
    
    public UnidadDTO getUnidadById(Long id){
        Unidad unidad = unidadRepository.findById(id)
                .orElseThrow(() -> new UnidadNotFoundException(id));
        
        return unidadMapper.unidadToDto(unidad);
    }
    
    public Unidad updateUnidad(UnidadDTO unidadDto, Long id){
        Dependencia dependencia = dependenciaService.getDependenciaById(unidadDto.getDependencia_id());
        
        Unidad newUnidad = unidadMapper.unidadDtoToEntity(unidadDto);
        
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
    
    public List<UnidadDTO> getUnidadByDependencia(Long dependenciaId){
        List<Unidad> listUnidades = unidadRepository.getUnidadByDependencia(dependenciaId);
        
        return unidadMapper.unidadToDtoList(listUnidades);
    }
}
