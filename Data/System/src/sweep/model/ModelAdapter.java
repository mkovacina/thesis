/*
 * Created on Mar 9, 2004
 */

package sweep.model;

import sweep.core.agent.State;


/**
 * @author orbital
 */
public abstract class ModelAdapter implements Model
{
	/*
	 * (non-Javadoc)
	 * 
	 * @see hive.model.Model#initialize()
	 */
	public void initialize()
	{
		throw new Error( "not supported" );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hive.model.Model#update()
	 */
	public void update()
	{
		throw new Error( "not supported" );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hive.model.Model#initialize(org.omg.PortableServer.POAManagerPackage.State)
	 */
	public void initialize( State state )
	{
		throw new Error( "not supported" );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hive.model.Model#update(org.omg.PortableServer.POAManagerPackage.State)
	 */
	public void update( State state )
	{
		throw new Error( "not supported" );
	}

	public State getState()
	{
		throw new Error( "not supported" );
	}

	public Object query( String queryString )
	{
		throw new Error( "not supported" );
	}
}