package fr.diginamic.census.dao;

import fr.diginamic.census.model.City;
import fr.diginamic.census.model.Department;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;

import org.springframework.stereotype.Service;

/**
 * DAO for City Entity
 */
@Service
public class CityDAO
{
	// Manager
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Extracts All Cities in DB
	 * 
	 * @return all Cities
	 */
	@Transactional
	public List<City> extractCities()
	{
		return entityManager.createQuery("SELECT c FROM City c", City.class).getResultList();
	}

	/**
	 * Extracts city by ID
	 * 
	 * @param id ID of the City to Extract
	 * @return Corresponding City Entity
	 */
	@Transactional
	public City extractCity(int id)
	{
		return entityManager.find(City.class, id);
	}

	/**
	 * Extracts City by Name
	 * 
	 * @param name Name of the City to Extract
	 * @return Corresponding City Entity
	 */
	@Transactional
	public City extractCityByName(String name)
	{
		return entityManager.createQuery("SELECT c FROM City c WHERE c.name = :name", City.class)
				.setParameter("name", name).getSingleResult();
	}

	/**
	 * Adds a City in DB
	 * 
	 * @param city City to add
	 * @return the City Entity
	 */
	@Transactional
	public City insertCity(City city)
	{
		entityManager.persist(city);
		return city;
	}

	/**
	 * Edits a City's data using another City's
	 * 
	 * @param id ID of the city to Edit
	 * @param data City from which Name and PopCount will be copied
	 * @return new City Entity
	 */
	@Transactional
	public City editCity(int id, City data)
	{
		City city = entityManager.find(City.class, id);
		
		if (city != null)
		{
			city.setName(data.getName());
			city.setPopCount(data.getPopCount());
		}
		
		return city;
	}

	/**
	 * Deletes City by ID
	 * 
	 * @param id ID of the City to remove from DB
	 */
	@Transactional
	public void deleteCity(int id)
	{
		City city = entityManager.find(City.class, id);
		
		if (city != null) 
			entityManager.remove(city);
	}

	/**
	 * Changes a City's department by ID
	 * 
	 * @param id ID of the City to remove from DB
	 */
	@Transactional
	public void setDepartment(int idCity, int idDepartment)
	{
	    City city = entityManager.find(City.class, idCity);
	    
	    if (city != null)
	    {
	        Department department = entityManager.find(Department.class, idDepartment);
	        
	        if (department != null)
	        {
	            city.setDepartment(department);
	            entityManager.merge(city);
	        } else
	        {
	            throw new IllegalArgumentException("Department not found with ID: " + idDepartment);
	        }
	    } else
	    {
	        throw new IllegalArgumentException("City not found with ID: " + idCity);
	    }
	}
}
