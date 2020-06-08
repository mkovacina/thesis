/*
 * Created on Mar 11, 2004
 */
package sweep.environment;

import java.util.Map;

import sweep.ice.EnvironmentConnector;


/**
 * @author orbital
 */
public abstract class Update
{
	protected final Map parameters;
	protected EnvironmentModel model;
	protected EnvironmentConnector connector;

	/**
	 *  
	 */
	public Update( Map parameters )
	{
		this.parameters = parameters;
	}

	public void initialize( EnvironmentModel model )
	{
		this.model = model;
		this.connector = model.connector;
	}

	public abstract void execute();
}