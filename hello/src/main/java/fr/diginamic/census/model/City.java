package fr.diginamic.census.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
//import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
//import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

/**
 * Represents a City with a name and a population count
 */
@Entity
//@Table(name="city")
public class City
{
	// ID of the object
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Min(value = 1, message="ID cannot be negative.")
	private int id;
	
	// City Name
	@NotNull(message="City name cannot be Null")
    @Size(min=2, message="City name must have at least two letters")
    @Column(name="name", nullable=false)
	private String name;
	
	// Population Count
	@Positive(message="Population count must be positive")
    @Column(name="population")
	private int popCount;
	
    // Reference to the City's Department
    @ManyToOne
    private Department department;
	
	/**
	 * Basic City Builder with both arguments
	 * 
	 * @param name Name of the City
	 * @param popCount Total Population Count
	 */
	public City(String name, int popCount)
	{
		setName(name);
		setPopCount(popCount);
	}
	
	/**
	 * Default constructor, just in case it's needed
	 */
	public City() { }
	
	/**
	 * ID Getter
	 * 
	 * @return The City's ID
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
	 * @return The City's Name
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * Name Setter
	 * 
	 * @param name The name to be set
	 */
	public void setName(String name)
	{
		this.name = name;
	}
	
	/**
	 * Population Count Getter
	 * 
	 * @return The City's Population Count
	 */
	public int getPopCount()
	{
		return popCount;
	}
	
	/**
	 * Population Count Setter
	 * 
	 * @param popCount The popCount to be set
	 */
	public void setPopCount(int popCount)
	{
		this.popCount = popCount;
	}
	
	/**
	 * Department Getter
	 * 
	 * @return The City's Department
	 */
	public Department getDepartment()
	{
		return department;
	}
	
	/**
	 * Department Setter
	 * 
	 * @param department The Department to be set
	 */
	public void setDepartment(Department department)
	{
		this.department = department;
	}
}
