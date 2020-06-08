/*
 * Created on Sep 22, 2004
 */
package grid.model.sensor;

import grid.environment.BasicGridModel;
import grid.model.GridAvatar;

import java.awt.Point;
import java.util.Map;

import miko.math.Mod;
import sweep.environment.SweepEnvironment;
import sweep.model.AvatarModel;
import sweep.model.sensor.BooleanValue;
import sweep.model.sensor.Sensor;
import sweep.model.sensor.Value;


/**
 * @author orbital
 */
public class NeighborSensor2 implements Sensor
{
	protected final String environmentNameRef;

	protected BasicGridModel environmentModel;

	protected Value value;

	protected final double minTheta;
	protected final double maxTheta;
	protected final double distance;
	protected final boolean invert;

	public NeighborSensor2( Map attributes )
	{
		super();

		this.environmentNameRef = ( String )attributes.get( "environmentReference" );
		value = BooleanValue.FALSE;
		this.minTheta = Double.parseDouble( ( String )attributes.get( "minTheta" ) );
		this.maxTheta = Double.parseDouble( ( String )attributes.get( "maxTheta" ) );
		this.distance = Double.parseDouble( ( String )attributes.get( "distance" ) );
		invert = attributes.containsKey( "invert" );
	}

	public void initialize( AvatarModel model, Map environmentMap )
	{
		String environmentName = ( String )model.query( environmentNameRef );
		environmentModel = ( BasicGridModel )( ( SweepEnvironment )environmentMap.get( environmentName ) ).getModel();
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
	 * @see sweep.model.sensor.Sensor#update(sweep.model.AvatarModel,
	 *      java.util.Map)
	 */
	public void update( AvatarModel model, Map environmentMap )
	{
		Point[] locations = ( Point[] )environmentModel.query( "positions" );
		Point pt = ( ( GridAvatar )model ).getLocation();

		value = BooleanValue.FALSE;

		for( int i = 0; i < locations.length; i++ )
		{
			double dist = pt.distance( locations[i] );

			if ( dist == 0 )
			{
				// skip self
				continue;
			}

			if ( dist <= distance )
			{
				double dx = locations[i].x - pt.x;
				double dy = locations[i].y - pt.y;

				//double theta = Mod.eval(StrictMath.atan2( dy, dx
				// )+((invert)?Math.PI:0), 2*Math.PI);

				double theta = StrictMath.atan2( dy, dx );
				if ( invert )
				{
					theta = Mod.eval( theta + Math.PI, 2 * Math.PI );
				}

				// Logic inserted above to reduce some of the modding
				//if ( invert )
				//{
				//	theta = Mod.eval(theta+Math.PI, 2*Math.PI);
				//}

				if ( minTheta <= theta && theta < maxTheta )
				{
					value = BooleanValue.TRUE;
					break;
				}
			}
		}

	}
}