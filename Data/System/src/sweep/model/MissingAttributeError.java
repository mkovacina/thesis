package sweep.model;

/**
 * Description: MissingAttributeError is thrown when an undefined attribute
 * value is encountered.
 * 
 * @author Michael A. Kovacina
 */
public class MissingAttributeError extends Error
{

	/**
	 * @param string
	 */
	public MissingAttributeError( String attributeName )
	{
		super( "Missing attribute '" + attributeName + "'" );
	}
}