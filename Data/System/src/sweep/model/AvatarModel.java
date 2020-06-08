package sweep.model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import sweep.ice.ModelConnector;
import sweep.model.action.Action;
import sweep.model.sensor.Sensor;


/**
 * @author orbital
 */
public class AvatarModel extends SweepModel
{
	protected final ModelConnector connector;
	protected final Map sensorMap;
	protected final Map actions;
	protected final String[] sensorNames;
	protected final Initializer[] initializers;
	protected final Update[] updates;

	protected Map sensorValues;

	/**
	 * @param connector -
	 *            communications conduit
	 * @param initializers -
	 *            initialization actions
	 * @param updates -
	 *            update actions
	 * @param attributes -
	 *            attribute values
	 * @param sensorMap -
	 *            <name,sensor>mappings
	 * @param actions -
	 *            <name,action>mappings
	 */
	public AvatarModel( final ModelConnector connector, final Initializer[] initializers, final Update[] updates,
			final Map attributes, final Map sensorMap, final Map actions )
	{
		super( attributes );
		this.initializers = initializers;
		this.updates = updates;
		this.connector = connector;
		this.sensorMap = sensorMap;
		this.actions = actions;
		this.sensorNames = ( String[] )sensorMap.keySet().toArray( new String[0] );
		this.sensorValues = new HashMap();
	}

	/**
	 * @see sweep.model.ModelAdapter#initialize()
	 */
	public void initialize()
	{
		for( int x = 0; x < initializers.length; x++ )
		{
			initializers[x].execute( this, connector.getEnvironmentMap() );
		}

		Iterator actionIterator = actions.values().iterator();

		while( actionIterator.hasNext() )
		{
			( ( Action )actionIterator.next() ).initialize( this, connector.getEnvironmentMap() );
		}

		Iterator sensorIterator = sensorMap.values().iterator();

		while( sensorIterator.hasNext() )
		{
			( ( Sensor )sensorIterator.next() ).initialize( this, connector.getEnvironmentMap() );
		}

		state.put( "active", Boolean.TRUE );
	}

	/**
	 * @see sweep.model.ModelAdapter#update()
	 */
	public void update()
	{
		throw new Error( "not supported" );
		//for ( int x = 0; x < updates.length; x++ )
		//{
		//	updates[x].execute(this, connector.getEnvironmentMap());
		//}

		// REFACTOR: does this need to be done every time?
		//state.put("sensorValues", sensorValues);
	}

	/**
	 * Iterate over all sensors and update their values
	 */
	public void updateSensors()
	{
		// REFACTOR: used to be an Update class
		Map environmentMap = connector.getEnvironmentMap();

		for( int x = 0; x < sensorNames.length; x++ )
		{
			Sensor sensor = ( Sensor )sensorMap.get( sensorNames[x] );
			sensor.update( this, environmentMap );
			sensorValues.put( this.sensorNames[x], sensor.getValue() );
		}

		state.put( "sensorValues", sensorValues );
	}

	/**
	 * Execute all the specified actions
	 */
	public void executeNextAction()
	{
		// REFACTOR: used to be an Update class
		String[] command = connector.getCommands();

		for( int x = 0; x < command.length; x++ )
		{
			executeAction( command[x] );
		}
	}

	// XXX: yuk
	/**
	 * @param actionName
	 *            The name of the action to execute
	 */
	public void executeAction( String actionName )
	{
		Action action = ( Action )actions.get( actionName );

		if ( action == null )
		{
			throw new NullPointerException( "Action '" + actionName + "' is invalid" );
		}

		action.invoke( this, connector.getEnvironmentMap() );
	}
}