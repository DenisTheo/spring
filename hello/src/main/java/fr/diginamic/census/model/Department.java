package fr.diginamic.census.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

/**
 * Department Entity that holds multiple cities
 */
@Entity
//@Table(name="department")
public class Department
{
	// Department's Zip code
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotBlank(message="Department Zip Code is required")
	@Min(value=001, message="Department code must be between 001 and 999")
	@Max(value=999, message="Department code must be between 001 and 999")
	private int code;
	
	// Name of the Department
	@NotBlank(message="Department name is required")
    @Size(min=3, message="Department name must have at least 3 characters")
    private String name;

	// All the Cities within this Department
	@OneToMany(mappedBy="department", cascade=CascadeType.ALL)
    private List<City> cities = new ArrayList<>();

	/**
	 * Default constructor, just in case it's needed
	 */
	public Department()	{ }

	/**
	 * Basic Department Builder
	 * 
	 * @param name Name of the City
	 */
	public Department(String name)
	{
		this.name = name;
	}

	/**
	 * Zip Code Getter
	 * 
	 * @return The Zip Code
	 */
	public int getZipCode()
	{
		return code;
	}

	/**
	 * Zip Code Setter
	 * 
	 * @param code The Zip Code to be set
	 */
	public void setZipCode(int code)
	{
		this.code = code;
	}

	/**
	 * Name Getter
	 * 
	 * @return The Department Name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Name Setter
	 * 
	 * @param name The Name to be set
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * Cities Getter
	 * 
	 * @return The Cities in this Department
	 */
	public List<City> getCities()
	{
		return cities;
	}

	/**
	 * Cities Setter
	 * 
	 * @param cities The Cities list to be set
	 */
	public void setCities(List<City> cities)
	{
		this.cities = cities;
	}
	
	/**
	 * Gets population of the combined cities of the department
	 * 
	 * @return total population of cities within department
	 */
	public int getPopulation()
	{
        return cities.stream().mapToInt(City::getPopCount).sum();
    }
}
