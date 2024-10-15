package fr.diginamic.census.controller;

import fr.diginamic.census.dto.CityDTO;
import fr.diginamic.census.model.City;
import fr.diginamic.census.model.Department;
import fr.diginamic.census.repository.CityRepository;
import fr.diginamic.census.service.CityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

/**
 * Controller for the City Entity
 */
@RestController
@RequestMapping("/cities")
public class CityController
{
	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
    private CityService cityService;

	/**
	 * Gets a paginated list of cities.
	 *
	 * @param page Page number (starts at 0)
	 * @param size Number of cities per page
	 * @return a paginated list of cities
	 */
	@GetMapping("/cities")
	public Page<City> listCities(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size)
	{
		return cityRepository.findAll(PageRequest.of(page, size));
	}

	/**
	 * Fetches a city based on its ID.
	 *
	 * @param id ID of the city to retrieve
	 * @return the City (as a response)
	 */
	@GetMapping("/{id}")
	public ResponseEntity<City> getCityById(@PathVariable int id)
	{
		return cityRepository.findById(id).map(city -> new ResponseEntity<>(city, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	/**
	 * Fetches cities based on the name prefix.
	 *
	 * @param name Prefix of the city name to search
	 * @return a list of matching cities
	 */
	@GetMapping("/searchByName/{name}")
	public List<City> getCitiesByNamePrefix(@PathVariable String name)
	{
		return cityRepository.findCityByName(name + "%");
	}

	/**
	 * Fetches cities with a population greater than a minimum.
	 *
	 * @param min Minimum population
	 * @return a list of cities with population greater than min
	 */
	@GetMapping("/searchByMinPop/{min}")
	public List<City> getCitiesByMinPopulation(@PathVariable int min)
	{
		return cityRepository.findCitiesWithPopOver(min);
	}

	/**
	 * Fetches cities with a population between min and max.
	 *
	 * @param min Minimum population
	 * @param max Maximum population
	 * @return a list of cities with population between min and max
	 */
	@GetMapping("/searchByPopRange/{min}/{max}")
	public List<City> getCitiesByPopulationRange(@PathVariable int min, @PathVariable int max)
	{
		return cityRepository.findCitiesWithPopWithinRange(min, max);
	}

	/**
	 * Fetches cities in a department with a population greater than a minimum.
	 *
	 * @param departmentId ID of the department
	 * @param min          Minimum population
	 * @return a list of cities
	 */
	@GetMapping("/searchByDepartmentAndMinPop/{departmentId}/{min}")
	public List<City> getCitiesInDepartmentByMinPopulation(@PathVariable int departmentId, @PathVariable int min)
	{
		Department department = new Department();
		department.setZipCode(departmentId); // Assuming you set the department by its ID
		return cityRepository.findCitiesWithPopOverInDepartment(department, min);
	}

	/**
	 * Fetches cities in a department with a population between min and max.
	 *
	 * @param departmentId ID of the department
	 * @param min          Minimum population
	 * @param max          Maximum population
	 * @return a list of cities
	 */
	@GetMapping("/searchByDepartmentAndPopRange/{departmentId}/{min}/{max}")
	public List<City> getCitiesInDepartmentByPopulationRange(@PathVariable int departmentId, @PathVariable int min,
			@PathVariable int max)
	{
		Department department = new Department();
		department.setZipCode(departmentId);
		return cityRepository.findCitiesWithPopWithinRangeInDepartment(department, min, max);
	}

	/**
	 * Fetches the N most populated cities in a department.
	 *
	 * @param departmentId ID of the department
	 * @param n            Number of cities to return
	 * @return a list of top N populated cities in the department
	 */
	@GetMapping("/topNCitiesInDepartment/{departmentId}/{n}")
	public List<City> getTopNCitiesInDepartment(@PathVariable int departmentId, @PathVariable int n)
	{
		Department department = new Department();
		department.setZipCode(departmentId);
		return cityRepository.findMostPopulatedCitiesInDepartment(department, PageRequest.of(0, n)).getContent();
	}

	/**
	 * Retrieves all cities.
	 *
	 * @return list of cities
	 */
	@GetMapping
	public ResponseEntity<List<City>> getAllCities()
	{
		return ResponseEntity.ok(cityService.getAllCities());
	}

	/**
	 * Adds a new city with validation.
	 *
	 * @param city City to add
	 * @return response
	 */
	@PostMapping("/add")
	public ResponseEntity<String> addCity(@Valid @RequestBody City city, BindingResult result)
	{
		if (result.hasErrors())
		{
			// If validation errors are detected, return 400 Bad Request
			return new ResponseEntity<>("Validation error: " + result.getFieldErrors(), HttpStatus.BAD_REQUEST);
		}

		cityRepository.save(city);
		return new ResponseEntity<>("City added successfully", HttpStatus.CREATED);
	}

	/**
	 * Edits an existing city with validation.
	 *
	 * @param id   ID of the city to edit
	 * @param data city object with updated data
	 * @return response
	 */
	@PutMapping("/edit/{id}")
	public ResponseEntity<String> editCity(@PathVariable int id, @Valid @RequestBody City data, BindingResult result)
	{
		if (result.hasErrors())
		{
			// If validation errors are detected, return 400 Bad Request
			return new ResponseEntity<>("Validation error: " + result.getFieldErrors(), HttpStatus.BAD_REQUEST);
		}

		return cityRepository.findById(id).map(existingCity ->
		{
			existingCity.setName(data.getName());
			existingCity.setPopCount(data.getPopCount());
			existingCity.setDepartment(data.getDepartment());
			cityRepository.save(existingCity);
			return new ResponseEntity<>("Successfully updated city", HttpStatus.OK);
		}).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	/**
     * Export cities to CSV.
     *
     * @return CSV file content as a String
     */
    @GetMapping("/export-csv")
    public String exportCitiesToCsv() {
        return cityService.exportCitiesToCsv();
    }

	/**
	 * Deletes a city.
	 *
	 * @param id ID of the city to delete
	 * @return response
	 */
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteCity(@PathVariable int id)
	{
		if (cityRepository.existsById(id))
		{
			cityRepository.deleteById(id);
			return new ResponseEntity<>("Successfully removed city", HttpStatus.OK);
		} else
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
