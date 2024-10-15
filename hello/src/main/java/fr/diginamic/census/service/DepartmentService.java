package fr.diginamic.census.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import fr.diginamic.census.dto.DepartmentDTO;
import fr.diginamic.census.exception.DepartmentNotFoundException;
import fr.diginamic.census.mapper.DepartmentMapper;
import fr.diginamic.census.model.Department;
import fr.diginamic.census.repository.DepartmentRepository;

public class DepartmentService
{
	@Autowired
	private DepartmentRepository departmentRepository;

	@Autowired
	private DepartmentMapper departmentMapper;

	public List<DepartmentDTO> getAllDepartments()
	{
		return departmentRepository.findAll().stream().map(departmentMapper::toDto).collect(Collectors.toList());
	}

	public DepartmentDTO getDepartmentByCode(String code)
	{
		Department department = departmentRepository.findById(code)
				.orElseThrow(() -> new DepartmentNotFoundException("Department not found with code: " + code));
		return departmentMapper.toDto(department);
	}

	public DepartmentDTO createDepartment(DepartmentDTO departmentDto)
	{
		Department department = departmentMapper.toEntity(departmentDto);
		department = departmentRepository.save(department);
		return departmentMapper.toDto(department);
	}

	public String exportDepartmentsToCSV()
	{
		List<Department> departments = departmentRepository.findAll();
		StringBuilder csvBuilder = new StringBuilder();
		csvBuilder.append("Code,Name,Population\n");
		
		for (Department department : departments)
		{
			csvBuilder.append(department.getZipCode()).append(',').append(department.getName()).append(',')
					.append(department.getPopulation()).append('\n');
		}
		
		return csvBuilder.toString();
	}
}
