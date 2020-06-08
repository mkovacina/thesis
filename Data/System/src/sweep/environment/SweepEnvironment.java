package sweep.environment;

import sweep.ice.EnvironmentConnector;


public class SweepEnvironment implements sweep.core.environment.Environment
{
	final protected EnvironmentModel model;

	/**
	 * @param initializers
	 * @param updates
	 * @param models
	 * @param avatars
	 */
	public SweepEnvironment( final EnvironmentModel model )
	{
		this.model = model;
	}

	public void initialize()
	{
		model.initialize();
	}

	public void update()
	{
		model.update();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hive.environment.SweepEnvironment#query(java.lang.String)
	 */
	public Object query( String queryString )
	{
		return model.query( queryString );
	}

	public String toString()
	{
		StringBuffer buffer = new StringBuffer( 64 );

		buffer.append( "**| Environment: " );
		buffer.append( model.getClass().getName() );
		buffer.append( '\n' );

		return buffer.toString();
	}

	// REFACTOR: yucky
	public void setConnector( EnvironmentConnector connector )
	{
		model.setConnector( connector );
	}

	// REFACTOR: this can be avoided somehow
	public EnvironmentModel getModel()
	{
		return model;
	}
}