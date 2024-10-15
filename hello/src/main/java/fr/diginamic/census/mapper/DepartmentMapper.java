package fr.diginamic.census.mapper;

import fr.diginamic.census.dto.DepartmentDTO;
import fr.diginamic.census.model.Department;

import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface DepartmentMapper
{
    DepartmentDTO toDto(Department department);

    Department toEntity(DepartmentDTO departmentDto);
}
