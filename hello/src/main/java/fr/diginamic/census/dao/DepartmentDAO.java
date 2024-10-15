package fr.diginamic.census.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.diginamic.census.model.Department;
import fr.diginamic.census.repository.DepartmentRepository;

import java.util.List;
import java.util.Optional;

@Component
public class DepartmentDAO
{
	@Autowired
	private DepartmentRepository departmentRepository;

	/**
	 * Retrieve all departments.
	 * 
	 * @return a list of all departments
	 */
	public List<Department> getAllDepartments()
	{
		return departmentRepository.findAll();
	}

	/**
	 * Retrieve a department by its code.
	 * 
	 * @param code the code of the department
	 * @return an Optional containing the department if found, or empty if not found
	 */
	public Optional<Department> getDepartmentByCode(String code)
	{
		return departmentRepository.findById(code);
	}

	/**
	 * Create or update a department.
	 * 
	 * @param department the department entity to save
	 * @return the saved department
	 */
	public Department saveDepartment(Department department)
	{
		return departmentRepository.save(department);
	}

	/**
     * Delete a department by its code.
     * 
     * @param code the code of the department to delete
     */
    public void deleteDepartment(String code)
    {
        departmentRepository.deleteById(code);
    }
}