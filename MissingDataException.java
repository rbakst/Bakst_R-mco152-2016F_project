package exceptions;

public class MissingDataException extends Exception
{
	public MissingDataException()
	{
		super("Some data is missing.");
	}
}
