/*
 * Created on Mar 10, 2004
 */

package sweep.environment;

import java.util.Map;

import sweep.ice.EnvironmentConnector;
import sweep.model.SweepModel;


/**
 * @author orbital
 */
public class EnvironmentModel extends SweepModel
{
	protected final Initializer[] initializers;
	protected final Update[] updates;
	protected EnvironmentConnector connector;

	/**
	 * @param connector
	 * @param initializers
	 * @param updates
	 * @param attributes
	 * @param sensors
	 * @param actions
	 */
	public EnvironmentModel( final Initializer[] initializers, final Update[] updates, final Map attributes )
	{
		super( attributes );
		this.initializers = initializers;
		this.updates = updates;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hive.model.Model#initialize()
	 */
	public void initialize()
	{
		for( int x = 0; x < initializers.length; x++ )
		{
			initializers[x].execute( this );
		}

		for( int x = 0; x < updates.length; x++ )
		{
			updates[x].initialize( this );
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hive.model.Model#update()
	 */
	public void update()
	{
		for( int x = 0; x < updates.length; x++ )
		{
			updates[x].execute();
		}
	}

	public Object query( String queryString )
	{
		return null;
	}

	// REFACTOR: yucky
	protected void setConnector( EnvironmentConnector connector )
	{
		this.connector = connector;
	}
}