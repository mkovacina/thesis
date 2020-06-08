/*
 * Created on Jan 29, 2004
 */

package sweep.model.sensor;

import java.util.Map;

import sweep.model.AvatarModel;


/**
 * @author orbital
 */
public class RandomSensor implements Sensor
{
	// REFACTOR: rethink the Sensor class heirarchy
	public RandomSensor( Map attributes )
	{
	}

	public Value getValue()
	{
		if ( Math.random() > 0.5 )
			return new BooleanValue( true );
		else
			return new BooleanValue( false );
	}

	public void initialize( AvatarModel model, Map environmentMap )
	{
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hive.model.sensor.Sensor#update()
	 */
	public void update( AvatarModel model, Map environmentMap )
	{
	}
}