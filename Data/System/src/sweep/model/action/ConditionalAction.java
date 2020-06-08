package sweep.model.action;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import sweep.controller.SensorValue;
import sweep.controller.TestValue;
import sweep.core.agent.State;
import sweep.model.AvatarModel;
import sweep.model.sensor.Value;


/**
 * Description: ConditionalAction Works like an if statement, executing one set
 * of actions if some sensor conditions are true, and another set if the sensor
 * condition is false.
 * 
 * @author Michael A. Kovacina
 */
public class ConditionalAction extends AbstractAction
{
	private List sensorTests;
	private List stateTests;
	private String[] thenActions;
	private String[] elseActions;

	/**
	 * @param parameters
	 */
	public ConditionalAction( Map parameters )
	{
		super( parameters );
	}

	/**
	 * @param sensorTests
	 *            The names and values of the target sensor
	 * @param stateTests
	 *            The name and value of the target state variables
	 * @param thenActions2
	 *            The list of actions to execute if the condition is true
	 * @param elseActions2
	 *            The list of action to execute if the condition is false
	 */
	public ConditionalAction( List sensorTests, List stateTests, String[] thenActions2, String[] elseActions2 )
	{
		super( Collections.EMPTY_MAP );
		this.sensorTests = sensorTests;
		this.stateTests = stateTests;
		this.thenActions = ( String[] )thenActions2.clone();
		this.elseActions = ( String[] )elseActions2.clone();
	}

	/**
	 * @see sweep.model.action.Action#invoke(sweep.model.AvatarModel,
	 *      java.util.Map)
	 */
	public void invoke( AvatarModel model, Map environmentMap )
	{
		State state = model.getState();
		Map sensorValues = ( Map )state.get( "sensorValues" );

		boolean result = true;
		
		for( int x = 0; result && x < sensorTests.size(); x++ )
		{
			SensorValue sensorValue = ( SensorValue )sensorTests.get( x );
			Value value = ( Value )sensorValues.get( sensorValue.name );
			if ( value == null )
				throw new Error( "Sensor value '" + sensorValue.name + "' not found" );

			result &= sensorValue.targetValue.equals( value );
		}

		for( int x = 0; result && x < stateTests.size(); x++ )
		{
			TestValue testValue = ( TestValue )stateTests.get( x );
			String value = ( String )state.get(testValue.name);

			if ( value == null )
				throw new Error( "Sensor value '" + testValue.name + "' not found" );

			result &= testValue.isEqualTo( value );
		}

		String[] actions = result ? thenActions : elseActions;

		for( int x = 0; x < actions.length; x++ )
		{
			model.executeAction( actions[x] );
		}
	}
}