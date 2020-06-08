package grid.model.sensor;

import grid.environment.BasicGridModel;
import grid.model.GridAvatar;

import java.awt.Point;
import java.util.Map;

import sweep.environment.SweepEnvironment;
import sweep.model.AvatarModel;
import sweep.model.sensor.BooleanValue;
import sweep.model.sensor.Sensor;
import sweep.model.sensor.Value;


/**
 * Description: NeighborSensor counts the number of agents within a certain area
 * 
 * @author Michael A. Kovacina
 */
public class NeighborSensor implements Sensor
{
	protected final String environmentNameRef;

	protected BasicGridModel environmentModel;

	protected Value value;

	int top;
	int left;
	int bottom;
	int right;

	/**
	 * @param attributes
	 */
	public NeighborSensor( Map attributes )
	{
		super();

		this.environmentNameRef = ( String )attributes.get( "environmentReference" );
		value = BooleanValue.FALSE;
		this.top = Integer.parseInt( ( String )attributes.get( "top" ) );
		this.left = Integer.parseInt( ( String )attributes.get( "left" ) );
		this.bottom = Integer.parseInt( ( String )attributes.get( "bottom" ) );
		this.right = Integer.parseInt( ( String )attributes.get( "right" ) );
	}

	/** 
	 * @see sweep.model.sensor.Sensor#initialize(sweep.model.AvatarModel, java.util.Map)
	 */
	public void initialize( AvatarModel model, Map environmentMap )
	{
		String environmentName = ( String )model.query( environmentNameRef );
		environmentModel = ( BasicGridModel )( ( SweepEnvironment )environmentMap.get( environmentName ) ).getModel();
	}

	/** 
	 * @see sweep.model.sensor.Sensor#getValue()
	 */
	public Value getValue()
	{
		return value;
	}

	/** 
	 * @see sweep.model.sensor.Sensor#update(sweep.model.AvatarModel, java.util.Map)
	 */
	public void update( AvatarModel model, Map environmentMap )
	{
		Point pt = ( ( GridAvatar )model ).getLocation();
		Point t = new Point();

		value = BooleanValue.FALSE;

		for( int y = top; y <= bottom; y++ )
		{
			for( int x = left; x <= right; x++ )
			{
				if ( x == 0 && y == 0 )
					continue;

				t.x = pt.x + x;
				t.y = pt.y + y;

				if ( environmentModel.locationClear( t ) == false )
				{
					value = BooleanValue.TRUE;
					break;
				}
			}
		}
	}
}