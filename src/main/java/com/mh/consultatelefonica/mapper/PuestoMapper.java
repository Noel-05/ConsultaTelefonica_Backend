/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mh.consultatelefonica.mapper;

import com.mh.consultatelefonica.dto.PuestoDTO;
import com.mh.consultatelefonica.model.Puesto;
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
public interface PuestoMapper {
    
    @Mapping(source = "puesto.rol.id", target = "rol_id")
    @Mapping(source = "puesto.rol.code", target = "rol_code")
    @Mapping(source = "puesto.rol.name", target = "rol_name")
    @Mapping(source = "puesto.unidad.id", target = "unidad_id")
    @Mapping(source = "puesto.unidad.code", target = "unidad_code")
    @Mapping(source = "puesto.unidad.name", target = "unidad_name")
    PuestoDTO puestoToDto(Puesto puesto);
    
    @InheritInverseConfiguration
    Puesto puestoDtoToEntity(PuestoDTO puestoDto);
    
    List<PuestoDTO> puestoToDtoList(List<Puesto> puestoList);
}
