/*
 * Created on Aug 7, 2004
 */
package standard.error;

/**
 * @author orbital
 */
public class ParameterNotFoundException extends RuntimeException
{
	public ParameterNotFoundException( String parameterName )
	{
		super( "Parameter '" + parameterName + "' not found" );
	}
}