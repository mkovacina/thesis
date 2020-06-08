/*
 * Created on Mar 14, 2004
 */
package sweep.model;

import java.util.Map;

import sweep.model.sensor.Sensor;


/**
 * @author orbital
 */
public class SensorUpdate extends Update
{
	/**
	 * @param parameters
	 */
	public SensorUpdate( Map parameters )
	{
		super( parameters );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sweep.avatar.Update#execute(sweep.avatar.AvatarModel)
	 */
	public void execute( AvatarModel model, Map environmentMap )
	{
		for( int x = 0; x < model.sensorNames.length; x++ )
		{
			Sensor sensor = ( Sensor )model.sensorMap.get( model.sensorNames[x] );
			sensor.update( model, environmentMap );
			model.sensorValues.put( model.sensorNames[x], sensor.getValue() );
		}
	}
}