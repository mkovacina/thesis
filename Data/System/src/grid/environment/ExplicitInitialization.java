package grid.environment;

import java.util.Collections;
import java.util.Map;

import sweep.environment.EnvironmentModel;
import sweep.environment.Initializer;


public class ExplicitInitialization extends Initializer
{
	/*
	 * Data format: [ x y value ]
	 */
	private int[][] data;

	/**
	 * @param parameters
	 */
	public ExplicitInitialization( Map parameters )
	{
		super( parameters );
	}

	protected ExplicitInitialization( int[][] data )
	{
		super( Collections.EMPTY_MAP );

		this.data = ( int[][] )data.clone();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sweep.environment.Initializer#execute(sweep.environment.EnvironmentModel)
	 */
	public void execute( EnvironmentModel model )
	{
		BasicGridModel gm = ( BasicGridModel )model;

		for( int i = 0; i < data.length; i++ )
		{
			int x = data[i][0];
			int y = data[i][1];
			int v = data[i][2];

			gm.set( x, y, v );
		}
	}

}