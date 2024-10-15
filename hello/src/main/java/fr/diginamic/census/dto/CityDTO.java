package fr.diginamic.census.dto;

public class CityDTO
{
	private String code;
	private int population;
	private String departmentCode;
	private String departmentName;

	// Getters and Setters
	public String getCode()
	{
		return code;
	}

	public void setCode(String code)
	{
		this.code = code;
	}

	public int getPopulation()
	{
		return population;
	}

	public void setPopulation(int population)
	{
		this.population = population;
	}

	public String getDepartmentCode()
	{
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode)
	{
		this.departmentCode = departmentCode;
	}

	public String getDepartmentName()
	{
		return departmentName;
	}

	public void setDepartmentName(String departmentName)
	{
		this.departmentName = departmentName;
	}
}
