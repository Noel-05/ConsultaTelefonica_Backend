/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mh.consultatelefonica.mapper;

import com.mh.consultatelefonica.dto.EmpleadoDTO;
import com.mh.consultatelefonica.model.Empleado;
import java.util.List;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

/**
 *
 * @author noel.renderos
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface EmpleadoMapper {
    
    @Mapping(source = "birth_date", target = "birth_date", dateFormat = "dd/MM/yyyy")
    @Mapping(source = "start_date", target = "start_date", dateFormat = "dd/MM/yyyy")
    @Mapping(source = "puesto.id", target = "puesto_id")
    @Mapping(source = "puesto.name", target = "puesto_name")
    @Mapping(source = "puesto.rol.id", target = "rol_id")
    @Mapping(source = "puesto.rol.code", target = "rol_code")
    @Mapping(source = "puesto.rol.name", target = "rol_name")
    @Mapping(source = "puesto.unidad.id", target = "unidad_id")
    @Mapping(source = "puesto.unidad.name", target = "unidad_name")
    @Mapping(source = "puesto.unidad.dependencia.id", target = "dependencia_id")
    @Mapping(source = "puesto.unidad.dependencia.name", target = "dependencia_name")
    EmpleadoDTO empleadoToDto(Empleado empleado);
    
    @InheritInverseConfiguration
    Empleado empleadoDtoToEntity(EmpleadoDTO empleadoDto);
    
    List<EmpleadoDTO> empleadoToDtoList(List<Empleado> empleadosList);
}
