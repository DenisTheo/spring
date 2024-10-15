package fr.diginamic.census.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import fr.diginamic.census.dto.CityDTO;
import fr.diginamic.census.model.City;

@Mapper(componentModel="spring")
public interface CityMapper
{
	@Mapping(source="department.code", target="departmentCode")
	@Mapping(source="department.name", target="departmentName")
	CityDTO toDto(City city);

	City toEntity(CityDTO cityDto);
}
