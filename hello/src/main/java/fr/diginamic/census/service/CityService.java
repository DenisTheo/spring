package fr.diginamic.census.service;

import fr.diginamic.census.dao.CityDAO;
import fr.diginamic.census.model.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service related to the City Entity
 */
@Service
public class CityService
{
	// DAO to manage the Entity
	@Autowired
	private CityDAO cityDao;

	/**
	 * Extracts all Cities in DB
	 * 
	 * @return all Cities
	 */
	public List<City> getAllCities()
	{
		return cityDao.extractCities();
	}

	/**
	 * Extracts City from ID
	 * 
	 * @param id ID of the City to Extract
	 * @return Corresponding City Entity
	 */
	public City extractCity(int id)
	{
		return cityDao.extractCity(id);
	}

	/**
	 * Extracts City from Name
	 * 
	 * @param name Name of the City to Extract
	 * @return Corresponding City Entity
	 */
	public City extractCity(String name)
	{
		return cityDao.extractCityByName(name);
	}

	/**
	 * Adds a City in DB
	 * 
	 * @param city City to add
	 * @return All Cities, including the new one
	 */
	public List<City> insertCity(City city)
	{
		cityDao.insertCity(city);
		return getAllCities();
	}

	/**
	 * Edits a City's data using another City's
	 * 
	 * @param id ID of the City to Edit
	 * @param data City from which Name and PopCount will be copied
	 * @return All Cities, including the new one
	 */
	public List<City> editCity(int id, City data)
	{
		cityDao.editCity(id, data);
		return getAllCities();
	}

	/**
	 * Deletes City by ID
	 * 
	 * @param id
	 * @return All Cities, including the new one
	 */
	public List<City> deleteCity(int id)
	{
		cityDao.deleteCity(id);
		return getAllCities();
	}

	public String exportCitiesToCsv()
	{
		// TODO Auto-generated method stub
		return null;
	}
}
