package fr.diginamic.census.controller;

import fr.diginamic.census.dto.DepartmentDTO;
import fr.diginamic.census.service.DepartmentService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/departments")
@Validated
public class DepartmentController
{
    @Autowired
    private DepartmentService departmentService;

    /**
     * Creates a new department.
     *
     * @param departmentDto the department details
     * @return the created department
     */
    @PostMapping
    public ResponseEntity<DepartmentDTO> createDepartment(@Valid @RequestBody DepartmentDTO departmentDto)
    {
        return ResponseEntity.ok(departmentService.createDepartment(departmentDto));
    }

    /**
     * Exports all departments to CSV.
     *
     * @return CSV file content as a String
     */
    @GetMapping("/export-csv")
    public String exportDepartmentsToCsv()
    {
        return departmentService.exportDepartmentsToCSV();
    }
}
