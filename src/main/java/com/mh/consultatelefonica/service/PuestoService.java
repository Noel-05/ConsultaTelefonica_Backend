/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mh.consultatelefonica.service;

import com.mh.consultatelefonica.dto.PuestoDTO;
import com.mh.consultatelefonica.dto.UnidadDTO;
import com.mh.consultatelefonica.exception.PuestoNotFoundException;
import com.mh.consultatelefonica.mapper.PuestoMapper;
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
    
    @Autowired
    private PuestoMapper puestoMapper;
    
    public Puesto savePuesto(PuestoDTO puestoDto){
        Rol findRol = rolService.getRolById(puestoDto.getRol_id());
        
        UnidadDTO unidadDto = unidadService.getUnidadById(puestoDto.getUnidad_id());
        Unidad findUnidad = unidadMapper.unidadDtoToEntity(unidadDto);
        
        Puesto newPuesto = puestoMapper.puestoDtoToEntity(puestoDto);
        newPuesto.setRol(findRol);
        newPuesto.setUnidad(findUnidad);
        
        return puestoRepository.save(newPuesto);
    }
    
    public List<PuestoDTO> getPuestos(){
        List<Puesto> puestoList = puestoRepository.findAll();
        
        return puestoMapper.puestoToDtoList(puestoList);
    }
    
    public PuestoDTO getPuestoById(Long id){
        Puesto puesto = puestoRepository.findById(id)
                .orElseThrow(() -> new PuestoNotFoundException(id));
        
        return puestoMapper.puestoToDto(puesto);
    }
    
    public Puesto updatePuesto(PuestoDTO puestoDto, Long id){
        Rol findRol = rolService.getRolById(puestoDto.getRol_id());
        
        UnidadDTO unidadDto = unidadService.getUnidadById(puestoDto.getUnidad_id());
        Unidad findUnidad = unidadMapper.unidadDtoToEntity(unidadDto);
        
        Puesto newPuesto = puestoMapper.puestoDtoToEntity(puestoDto);
        
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
