package fr.diginamic.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.util.ArrayList;
import java.util.List;

/**
 * Department Entity that holds multiple cities
 */
@Entity
//@Table(name="department")
public class Department
{
	// DB ID of the Department
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	// Name of the Department
	private String name;

	// Department's Zip code
	private String code;

	// All the Cities within this Department
	@OneToMany(mappedBy = "department")
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
	 * ID Getter
	 * 
	 * @return Department's ID
	 */
	public int getId()
	{
		return id;
	}

	/**
	 * ID Setter
	 * 
	 * @param id The ID to be set
	 */
	public void setId(int id)
	{
		this.id = id;
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
}
