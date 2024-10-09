package fr.diginamic.model;

/**
 * Represents a City with a name and a population count
 */
public class City
{
	// ID of the object
	private int id;
	// City Name
	private String name;
	// Population Count
	private int popCount;
	
	/**
	 * City Builder with 2 arguments
	 * 
	 * @param name Name of the City
	 * @param popCount Total Population Count
	 */
	public City(int id, String name, int popCount)
	{
		setId(id);
		setName(name);
		setPopCount(popCount);
	}
	
	/**
	 * ID Setter
	 * 
	 * @param id the ID to be set
	 */
	public void setId(int id)
	{
		this.id = id;
	}
	
	/**
	 * Name Setter
	 * 
	 * @param name the name to be set
	 */
	public void setName(String name)
	{
		this.name = name;
	}
	
	/**
	 * Population Count Setter
	 * 
	 * @param popCount the popCount to be set
	 */
	public void setPopCount(int popCount)
	{
		this.popCount = popCount;
	}
	
	/**
	 * ID Getter
	 */
	public int getId()
	{
		return id;
	}
	
	/**
	 * Name Getter
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * Population Count Getter
	 */
	public int getPopCount()
	{
		return popCount;
	}
}
