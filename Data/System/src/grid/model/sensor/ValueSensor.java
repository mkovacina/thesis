/*
 * Created on Mar 17, 2004
 */

package grid.model.sensor;

import grid.environment.BasicGridModel;
import grid.model.GridAvatar;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import sweep.environment.SweepEnvironment;
import sweep.model.AvatarModel;
import sweep.model.sensor.BooleanValue;
import sweep.model.sensor.Sensor;
import sweep.model.sensor.Value;


/**
 * @author orbital
 */
public abstract class ValueSensor implements Sensor
{
	protected final String environmentNameRef;
	protected final String rangeRef;

	protected BasicGridModel environmentModel;
	protected int range;

	protected Value value;

	/**
	 *  
	 */
	public ValueSensor( Map attributes )
	{
		super();

		this.environmentNameRef = ( String )attributes.get( "environmentReference" );
		this.rangeRef = ( String )attributes.get( "rangeReference" );
		value = BooleanValue.FALSE;
	}

	public void initialize( AvatarModel model, Map environmentMap )
	{
		String environmentName = ( String )model.query( environmentNameRef );
		environmentModel = ( BasicGridModel )( ( SweepEnvironment )environmentMap.get( environmentName ) ).getModel();
		range = Integer.parseInt( ( String )model.query( rangeRef ) );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sweep.model.sensor.Sensor#getValue()
	 */
	public Value getValue()
	{
		return value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sweep.model.sensor.Sensor#update(sweep.ice.ModelConnector)
	 */
	public void update( AvatarModel model, Map environmentMap )
	{
		GridAvatar gridModel = ( GridAvatar )model;

		Point loc = gridModel.getLocation();

		boolean found = false;

		List foundLocations = new ArrayList();

		// NOTE: this is <= not just <
		for( int dx = -range; dx <= range; dx++ )
		{
			for( int dy = -range; dy <= range; dy++ )
			{
				Point p = new Point( loc.x + dx, loc.y + dy );

				foundLocations.add( p );
			}
		}

		if ( found )
		{
			value = BooleanValue.TRUE;
			executeFound( ( GridAvatar )model, foundLocations );
		}
		else
		{
			value = BooleanValue.FALSE;
			executeNotFound( ( GridAvatar )model );
		}
	}

	protected abstract void executeFound( GridAvatar model, List pointArray );

	protected abstract void executeNotFound( GridAvatar model );
}