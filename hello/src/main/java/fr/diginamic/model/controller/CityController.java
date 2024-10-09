package fr.diginamic.model.controller;

import fr.diginamic.model.City;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/city")
public class CityController
{
	// probably should have went with a hashmap
	List<City> cities = new ArrayList<City>();
	// next unused ID number
	private int nextId = 1;
	
	/**
	 * Gives a list of Cities
	 * 
	 * @return a list of cities
	 */
	@GetMapping("/cities")
	public List<City> listCities()
	{
		if(cities == null || cities.isEmpty())
		{
			cities.add(new City(nextId++, "Paris", 2161000));
			cities.add(new City(nextId++, "Oslo", 634293));
			cities.add(new City(nextId++, "Tokyo", 14180000));
			cities.add(new City(nextId++, "Cardiff", 372089));
			cities.add(new City(nextId++, "Beijing", 21890000));
		}
		
		return cities;
	}
	
	/**
	 * Fetches a City from the list
	 * 
	 * @param id ID of the city to retrieve
	 * @return the City (as a response)
	 */
	@GetMapping("/{id}")
	public ResponseEntity<City> afficherVilleParId(@PathVariable int id)
	{
		for (City city : cities)
			if (city.getId() == id)
				return new ResponseEntity<>(city, HttpStatus.OK);
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	/**
	 * Adds a city to the list
	 * 
	 * @param city City to add
	 * @return response
	 */
	@PostMapping("/add")
	public ResponseEntity<String> addCity(@RequestBody City city)
	{
		for (City existingCity : cities)
			if (existingCity.getName().equalsIgnoreCase(city.getName()))
				return new ResponseEntity<>("City already Exists", HttpStatus.BAD_REQUEST);
		
		city.setId(nextId++);
		cities.add(city);
		return new ResponseEntity<>("Ville insérée avec succès", HttpStatus.OK);
	}
	
	/**
	 * Edit City from ID
	 * 
	 * @param id ID of the city to edit
	 * @param newCity city object to copy name and population count from
	 * @return response
	 */
	@PutMapping("/edit/{id}")
	public ResponseEntity<String> modifierVille(@PathVariable int id, @RequestBody City newCity)
	{
		for (int i=0; i<cities.size(); i++)
		{
			City city = cities.get(i);
			if (city.getId() == id)
			{
				city.setName(newCity.getName());
				city.setPopCount(newCity.getPopCount());
				return new ResponseEntity<>("Successfully updated City", HttpStatus.OK);
			}
		}

		return new ResponseEntity<>("Ville non trouvée", HttpStatus.NOT_FOUND);
	}
	
	/**
	 * Deletes a city from the list
	 * 
	 * @param id ID of the city to delete
	 * @return response
	 */
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> supprimerVille(@PathVariable int id)
	{
		for (int i=0; i<cities.size(); i++)
		{
			City city = cities.get(i);
			if (city.getId() == id)
			{
				cities.remove(i);
				return new ResponseEntity<>("Successfully removed city", HttpStatus.OK);
			}
		}

		return new ResponseEntity<>("Ville non trouvée", HttpStatus.NOT_FOUND);
	}
}
