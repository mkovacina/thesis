/*
 * Created on Mar 11, 2004
 */
package sweep.environment;

import java.util.Map;


/**
 * @author orbital
 */
public abstract class Initializer
{
	protected final Map parameters;

	/**
	 *  
	 */
	public Initializer( Map parameters )
	{
		this.parameters = parameters;
	}

	public abstract void execute( EnvironmentModel model );
}