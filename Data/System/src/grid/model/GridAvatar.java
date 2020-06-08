/*
 * Created on Jan 28, 2004
 */

package grid.model;

import grid.environment.LocationManager;

import java.awt.Point;
import java.util.Map;

import sweep.core.environment.Environment;
import sweep.environment.SweepEnvironment;
import sweep.ice.ModelConnector;
import sweep.model.AvatarModel;
import sweep.model.Initializer;
import sweep.model.Update;


/**
 * @author orbital
 */
public class GridAvatar extends AvatarModel
{
	protected Point location;
	protected Map environmentMap;
	protected String environmentName;
	protected Environment environment;
	protected LocationManager lm;
	protected int rows;
	protected int cols;

	/**
	 * @param connector
	 * @param initializers
	 * @param updates
	 * @param attributes
	 * @param sensors
	 * @param actions
	 */
	public GridAvatar( ModelConnector connector, Initializer[] initializers, Update[] updates, Map attributes,
			Map sensors, Map actions )
	{
		super( connector, initializers, updates, attributes, sensors, actions );

		environmentMap = connector.getEnvironmentMap();
		environmentName = ( String )attributes.get( "environment" );
		environment = ( Environment )environmentMap.get( environmentName );

		rows = Integer.parseInt( ( String )environment.query( "rows" ) );
		cols = Integer.parseInt( ( String )environment.query( "cols" ) );

		location = new Point();

		lm = ( LocationManager )( ( SweepEnvironment )environment ).getModel();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hive.model.Model#initialize()
	 */
	public void initialize()
	{
		super.initialize();
		state.put( "position", location );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hive.model.Model#update()
	 */
	public void update()
	{
		super.update();
	}

	public Object query( String queryString )
	{
		if ( "position".equalsIgnoreCase( queryString ) )
		{
			return location;
		}

		// XXX: how should an unsuccessful query be handled
		return super.query( queryString );
	}

	public Point getLocation()
	{
		return location;
	}

	public void setLocation( int x, int y )
	{
		// this method is used only for initialization...
		location.setLocation( x, y );
		lm.move( this, 0, 0 );
	}

	public boolean move( int dx, int dy )
	{
		return lm.move( this, dx, dy );
	}

	public String toString()
	{
		return "GridAgentModel(" + location.x + "," + location.y + ")";
	}
}