package fr.diginamic.census.exception;

public class CityNotFoundException extends RuntimeException
{
	private static final long serialVersionUID = 4800150091244539130L;

	public CityNotFoundException()
	{
		super("City not Found.");
	}
	
	public CityNotFoundException(String message)
	{
        super(message);
    }
}
