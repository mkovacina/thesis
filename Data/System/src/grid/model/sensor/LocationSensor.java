package grid.model.sensor;

import grid.environment.BasicGridModel;
import grid.model.GridAvatar;

import java.awt.Point;
import java.util.Map;

import sweep.environment.SweepEnvironment;
import sweep.model.AvatarModel;
import sweep.model.MissingAttributeError;
import sweep.model.sensor.BooleanValue;
import sweep.model.sensor.Sensor;
import sweep.model.sensor.Value;
import sweep.reference.Reference;
import sweep.reference.ReferenceFactory;


/**
 * Description: LocationSensor evaluates to true when an agent is on a certain
 * location. This will eventually evolve to be more complex, but not right now.
 * 
 * @author Michael A. Kovacina
 */
public class LocationSensor implements Sensor
{
	protected final String environmentNameRef;

	protected BasicGridModel environmentModel;

	protected Value sensorValue;
	private Reference locationReference;

	/**
	 * Attributes required to be present
	 * <ul>
	 * <li>environment - attribute reference for the environment</li>
	 * <li>location - attribute reference for the target location</li>
	 * </ul>
	 * 
	 * @param attributes
	 */
	public LocationSensor( Map attributes )
	{
		super();

		if ( attributes.containsKey( "environment" ) == false )
		{
			throw new MissingAttributeError( "environment" );
		}

		if ( attributes.containsKey( "location" ) == false )
		{
			throw new MissingAttributeError( "location" );
		}

		this.environmentNameRef = ( String )attributes.get( "environment" );
		this.locationReference = ReferenceFactory.createReference( ( String )attributes.get( "location" ) );

		sensorValue = BooleanValue.FALSE;
	}

	/**
	 * @see sweep.model.sensor.Sensor#initialize(sweep.model.AvatarModel,
	 *      java.util.Map)
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
		return sensorValue;
	}

	/**
	 * @see sweep.model.sensor.Sensor#update(sweep.model.AvatarModel,
	 *      java.util.Map)
	 */
	public void update( AvatarModel model, Map environmentMap )
	{
		sensorValue = BooleanValue.FALSE;
		GridAvatar gridModel = ( GridAvatar )model;
		Point loc = gridModel.getLocation();
		Point target = ( Point )locationReference.getValue( model, environmentMap );

		if ( loc.equals( target ) )
		{
			sensorValue = BooleanValue.TRUE;
		}
	}
}