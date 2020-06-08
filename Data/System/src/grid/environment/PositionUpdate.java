/*
 * Created on Mar 18, 2004
 */
package grid.environment;

import java.awt.Point;
import java.util.Map;

import miko.query.Queryable;
import sweep.environment.EnvironmentModel;
import sweep.environment.Update;


/**
 * @author orbital
 */
public class PositionUpdate extends Update
{
	private BasicGridModel gmodel;
	private Queryable[] models;

	/**
	 * @param parameters
	 */
	public PositionUpdate( Map parameters )
	{
		super( parameters );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sweep.environment.Update#initialize(sweep.environment.EnvironmentModel)
	 */
	public void initialize( EnvironmentModel model )
	{
		super.initialize( model );

		this.gmodel = ( BasicGridModel )model;
		this.models = connector.models;

		gmodel.positions = new Point[this.models.length];

		for( int x = 0; x < models.length; x++ )
		{
			gmodel.positions[x] = ( Point )models[x].query( "position" );
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sweep.environment.Update#execute(sweep.environment.EnvironmentModel)
	 */
	public void execute()
	{
		for( int i = 0; i < models.length; i++ )
		{
			Point p = ( Point )models[i].query( "position" );

			gmodel.positions[i].x = p.x;
			gmodel.positions[i].y = p.y;
		}
	}
}