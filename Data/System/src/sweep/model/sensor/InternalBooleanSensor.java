package sweep.model.sensor;

import java.util.Map;

import sweep.model.AvatarModel;


/**
 * Description: InternalBooleanSensor returns the boolean value of the internal
 * setting of the agent.
 * 
 * @author Michael A. Kovacina
 */
public class InternalBooleanSensor implements Sensor
{
	private String attrName;
	protected Value sensorValue;

	// XXX: rethink the Sensor class heirarchy
	/**
	 * @param attributes
	 */
	public InternalBooleanSensor( Map attributes )
	{
		attrName = ( String )attributes.get( "attrName" );
	}

	/**
	 * @see sweep.model.sensor.Sensor#getValue()
	 */
	public Value getValue()
	{
		return sensorValue;
	}

	/**
	 * @see sweep.model.sensor.Sensor#initialize(sweep.model.AvatarModel,
	 *      java.util.Map)
	 */
	public void initialize( AvatarModel model, Map environmentMap )
	{
	}

	/**
	 * @see sweep.model.sensor.Sensor#update(sweep.model.AvatarModel,
	 *      java.util.Map)
	 */
	public void update( AvatarModel model, Map environmentMap )
	{
		String value = ( String )model.query( attrName );

		if ( value.equals( "true" ) )
		{
			sensorValue = BooleanValue.TRUE;
		}
		else
		{
			sensorValue = BooleanValue.FALSE;
		}
	}
}