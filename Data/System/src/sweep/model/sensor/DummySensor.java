/*
 * Created on Jan 29, 2004
 */

package sweep.model.sensor;

import java.util.Map;

import sweep.model.AvatarModel;


/**
 * @author orbital
 */
public class DummySensor implements Sensor
{
	// REFACTOR: rethink the Sensor class heirarchy
	public DummySensor( Map attributes )
	{
	}

	public Value getValue()
	{
		return new BooleanValue( true );
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