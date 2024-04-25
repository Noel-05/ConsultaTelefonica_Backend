/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mh.consultatelefonica.service;

import com.mh.consultatelefonica.dto.EmpleadoDTO;
import com.mh.consultatelefonica.dto.EmpleadoTelefonoDTO;
import com.mh.consultatelefonica.exception.EmpleadoTelefonoNotFoundException;
import com.mh.consultatelefonica.mapper.EmpleadoMapper;
import com.mh.consultatelefonica.mapper.EmpleadoTelefonoMapper;
import com.mh.consultatelefonica.model.Empleado;
import com.mh.consultatelefonica.model.EmpleadoTelefono;
import com.mh.consultatelefonica.model.TipoTelefono;
import com.mh.consultatelefonica.repository.EmpleadoTelefonoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author noel.renderos
 */
@Service
public class EmpleadoTelefonoService {
    
    @Autowired
    private EmpleadoService empleadoService;
    
    @Autowired 
    private EmpleadoMapper empleadoMapper;
    
    @Autowired
    private TipoTelefonoService tipoTelefonoService;
    
    @Autowired
    private EmpleadoTelefonoRepository empleadoTelefonoRepository;
    
    @Autowired 
    private EmpleadoTelefonoMapper empleadoTelefonoMapper;
    
    public EmpleadoTelefono saveEmpleadoTelefono(EmpleadoTelefono newEmpleadoTelefono, Long empleadoId, Long tipoTelefonoId){
        EmpleadoDTO empleadoDto = empleadoService.getEmpleadoById(empleadoId);
        Empleado findEmpleado = empleadoMapper.empleadoDtoToEntity(empleadoDto);
        TipoTelefono findTipoTelefono = tipoTelefonoService.getTipoTelefonoById(tipoTelefonoId);
        newEmpleadoTelefono.setEmpleado(findEmpleado);        
        newEmpleadoTelefono.setTipo_telefono(findTipoTelefono);
        
        return empleadoTelefonoRepository.save(newEmpleadoTelefono);
    }
    
    public List<EmpleadoTelefono> getEmpleadoTelefonos(){
        return empleadoTelefonoRepository.findAll();
    }
    
    public EmpleadoTelefono getEmpleadoTelefonoById(Long id){
        return empleadoTelefonoRepository.findById(id)
                .orElseThrow(() -> new EmpleadoTelefonoNotFoundException(id));
    }
    
    public EmpleadoTelefono updateEmpleadoTelefono(EmpleadoTelefono newEmpleadoTelefono, Long tipoTelefonoId, Long id){
        TipoTelefono findTipoTelefono = tipoTelefonoService.getTipoTelefonoById(tipoTelefonoId);
        
        return empleadoTelefonoRepository.findById(id)
                .map(empleadoTelefono -> {
                    empleadoTelefono.setPhone_number(newEmpleadoTelefono.getPhone_number());
                    empleadoTelefono.setTipo_telefono(findTipoTelefono);
                    
                    return empleadoTelefonoRepository.save(empleadoTelefono);
                })
                .orElseThrow(() -> new EmpleadoTelefonoNotFoundException(id));
    }
    
    public String deleteEmpleadoTelefono(Long id){
        if(!empleadoTelefonoRepository.existsById(id)){
            throw new EmpleadoTelefonoNotFoundException(id);
        }
        empleadoTelefonoRepository.deleteById(id);
        
        return "Empleado Telefono with id " + id + " has been succesfully deleted";
    }
    
    public List<EmpleadoTelefonoDTO> getTelefonosByEmpleadoId(Long empleadoId){
        List<EmpleadoTelefono> listEmpleadoTelefono = empleadoTelefonoRepository.getTelefonosByEmpleadoId(empleadoId);
        return empleadoTelefonoMapper.empleadoTelefonoToDtoList(listEmpleadoTelefono);
    }
}
