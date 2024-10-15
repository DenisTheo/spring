package fr.diginamic.census.exception;

public class DepartmentNotFoundException extends RuntimeException
{
	private static final long serialVersionUID = 1440876556717429304L;

	public DepartmentNotFoundException()
	{
		super("Department not Found.");
	}
	
	public DepartmentNotFoundException(String message)
	{
        super(message);
    }
}
