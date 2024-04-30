/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mh.consultatelefonica.service;

import com.mh.consultatelefonica.dto.EmpleadoDTO;
import com.mh.consultatelefonica.dto.PuestoDTO;
import com.mh.consultatelefonica.exception.EmpleadoNotFoundException;
import com.mh.consultatelefonica.mapper.EmpleadoMapper;
import com.mh.consultatelefonica.mapper.PuestoMapper;
import com.mh.consultatelefonica.model.Empleado;
import com.mh.consultatelefonica.model.Puesto;
import com.mh.consultatelefonica.repository.EmpleadoRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
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
    private PuestoMapper puestoMapper;
    
    @Autowired
    private EmpleadoRepository empleadoRepository;
    
    @Autowired
    private EmpleadoMapper empleadoMapper;
    
    public Empleado saveEmpleado(EmpleadoDTO empleadoDto){
        PuestoDTO puestoDto = puestoService.getPuestoById(empleadoDto.getPuesto_id());
        Puesto findPuesto = puestoMapper.puestoDtoToEntity(puestoDto);
        
        Empleado newEmpleado = empleadoMapper.empleadoDtoToEntity(empleadoDto);
        newEmpleado.setPuesto(findPuesto);
        
        return empleadoRepository.save(newEmpleado);
    }
    
    public List<EmpleadoDTO> getEmpleados(){
        List<Empleado> empleadoList = empleadoRepository.findAll();
        
        return empleadoMapper.empleadoToDtoList(empleadoList);
    }
    
    public EmpleadoDTO getEmpleadoById(Long id){
        Empleado empleado = empleadoRepository.findById(id)
                .orElseThrow(() -> new EmpleadoNotFoundException(id));
        
        return empleadoMapper.empleadoToDto(empleado);
    }
    
    public Empleado updateEmpleado(EmpleadoDTO empleadoDto, Long id){
        PuestoDTO puestoDto = puestoService.getPuestoById(empleadoDto.getPuesto_id());
        Puesto findPuesto = puestoMapper.puestoDtoToEntity(puestoDto);
        
        Empleado newEmpleado = empleadoMapper.empleadoDtoToEntity(empleadoDto);
        
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
    
    public List<EmpleadoDTO> filterEmpleados(String firstname, String lastname, String carnet){
        String employeeFilter = "";
        List<Empleado> empleadosList = empleadoRepository.findAll();
        
        if(firstname != null && !firstname.equals("")){
            employeeFilter += 'F';
        }
        if(lastname != null && !lastname.equals("")){
            employeeFilter += 'L';
        }
        if(carnet != null && !carnet.equals("")){
            employeeFilter += 'C';
        }
        
        switch (employeeFilter) {
            case "F":
                empleadosList = empleadosList.stream()
                        .filter(empleado -> empleado.getFirst_name().toLowerCase().contains(firstname.toLowerCase()))
                        .collect(Collectors.toList());
                break;
                
            case "L":
                empleadosList = empleadosList.stream()
                        .filter(empleado -> empleado.getLast_name().toLowerCase().contains(lastname.toLowerCase()))
                        .collect(Collectors.toList());
                break;
                
            case "C":
                empleadosList = empleadosList.stream()
                        .filter(empleado -> empleado.getCarnet().toLowerCase().contains(carnet.toLowerCase()))
                        .collect(Collectors.toList());
                break;
                
            case "FL":
                empleadosList = empleadosList.stream()
                        .filter(empleado -> empleado.getFirst_name().toLowerCase().contains(firstname.toLowerCase()))
                        .filter(empleado -> empleado.getLast_name().toLowerCase().contains(lastname.toLowerCase()))
                        .collect(Collectors.toList());
                break;
                
            case "FC":
                empleadosList = empleadosList.stream()
                        .filter(empleado -> empleado.getFirst_name().toLowerCase().contains(firstname.toLowerCase()))
                        .filter(empleado -> empleado.getCarnet().toLowerCase().contains(carnet.toLowerCase()))
                        .collect(Collectors.toList());
                break;
                
            case "LC":
                empleadosList = empleadosList.stream()
                        .filter(empleado -> empleado.getLast_name().toLowerCase().contains(lastname.toLowerCase()))
                        .filter(empleado -> empleado.getCarnet().toLowerCase().contains(carnet.toLowerCase()))
                        .collect(Collectors.toList());
                break;
                
            case "FLC":
                empleadosList = empleadosList.stream()
                        .filter(empleado -> empleado.getFirst_name().toLowerCase().contains(firstname.toLowerCase()))
                        .filter(empleado -> empleado.getLast_name().toLowerCase().contains(lastname.toLowerCase()))
                        .filter(empleado -> empleado.getCarnet().toLowerCase().contains(carnet.toLowerCase()))
                        .collect(Collectors.toList());
                break;
                
            default:
                return new ArrayList<>();
        }
                
        return empleadoMapper.empleadoToDtoList(empleadosList);
    }
    
    public List<EmpleadoDTO> getEmpleadosConTelefonos(){
        List<Empleado> empleadosList = empleadoRepository.getEmpleadosConTelefonos();
        
        return empleadoMapper.empleadoToDtoList(empleadosList);
    }
    
    public List<EmpleadoDTO> getEmpleadosByUnidad(Long unidadId){
        List<Empleado> empleadosList = empleadoRepository.getEmpleadosByUnidad(unidadId);
        
        return empleadoMapper.empleadoToDtoList(empleadosList);
    }
    
    public List<EmpleadoDTO> getEmpleadosCumpleanios(){
        List<Empleado> empleadosList = empleadoRepository.getEmpleadosCumpleanieros();
        
        return empleadoMapper.empleadoToDtoList(empleadosList);
    }
}
