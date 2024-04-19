/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mh.consultatelefonica.service;

import com.mh.consultatelefonica.dto.EmpleadoDTO;
import com.mh.consultatelefonica.exception.EmpleadoNotFoundException;
import com.mh.consultatelefonica.mapper.EmpleadoMapper;
import com.mh.consultatelefonica.model.Empleado;
import com.mh.consultatelefonica.model.Puesto;
import com.mh.consultatelefonica.repository.EmpleadoRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author noel.renderos
 */
@Service
public class EmpleadoService {
    
    @Autowired
    private PuestoService puestoService;
    
    @Autowired
    private EmpleadoRepository empleadoRepository;
    
    @Autowired
    private EmpleadoMapper empleadoMapper;
    
    public Empleado saveEmpleado(Empleado newEmpleado, Long puestoId){
        Puesto findPuesto = puestoService.getPuestoById(puestoId);
        newEmpleado.setPuesto(findPuesto);
        
        return empleadoRepository.save(newEmpleado);
    }
    
    public List<Empleado> getEmpleados(){
        return empleadoRepository.findAll();
    }
    
    public Empleado getEmpleadoById(Long id){
        return empleadoRepository.findById(id)
                .orElseThrow(() -> new EmpleadoNotFoundException(id));
    }
    
    public Empleado updateEmpleado(Empleado newEmpleado, Long puestoId, Long id){
        Puesto findPuesto = puestoService.getPuestoById(puestoId);
        
        return empleadoRepository.findById(id)
                .map(empleado -> {
                    empleado.setCarnet(newEmpleado.getCarnet());
                    empleado.setDui(newEmpleado.getDui());
                    empleado.setFirst_name(newEmpleado.getFirst_name());
                    empleado.setLast_name(newEmpleado.getLast_name());
                    empleado.setBirth_date(newEmpleado.getBirth_date());
                    empleado.setStart_date(newEmpleado.getStart_date());
                    empleado.setPuesto(findPuesto);
                    
                    return empleadoRepository.save(empleado);
                })
                .orElseThrow(() -> new EmpleadoNotFoundException(id));
    }
    
    public String deleteEmployee(Long id){
        if(!empleadoRepository.existsById(id)){
            throw new EmpleadoNotFoundException(id);
        }
        empleadoRepository.deleteById(id);
        
        return "Employee with id " + id + " has been succesfully deleted";
    }
    
    public List<EmpleadoDTO> filterEmpleados(String firstname, String lastname){
        if(!firstname.equals("") && lastname.equals("")){
            List<Empleado> empleadosList = empleadoRepository.filterEmpleadoFirstName(firstname);
            return empleadoMapper.empleadoToDtoList(empleadosList);
        }
        
        if(firstname.equals("") && !lastname.equals("")){
            List<Empleado> empleadosList = empleadoRepository.filterEmpleadoLastName(lastname);
            return empleadoMapper.empleadoToDtoList(empleadosList);
        }
        
        if(!firstname.equals("") && !lastname.equals("")){
            List<Empleado> empleadosList = empleadoRepository.filterEmpleadoFirstAndLastName(firstname, lastname);
            return empleadoMapper.empleadoToDtoList(empleadosList);
        }else{
            return new ArrayList<>();
        }
    }
}
