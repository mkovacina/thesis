package grid.model.sensor;

import grid.environment.BasicGridModel;
import grid.model.GridAvatar;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import sweep.core.agent.State;
import sweep.environment.SweepEnvironment;
import sweep.model.AvatarModel;
import sweep.model.MissingAttributeError;
import sweep.model.sensor.BooleanValue;
import sweep.model.sensor.Sensor;
import sweep.model.sensor.Value;


/**
 * Description: FilteredValueSensor evaluates to true when the target value is
 * within the specified range, and false when the value is not.
 * 
 * @author Michael A. Kovacina
 */
public class FilteredValueSensor implements Sensor
{
	protected final String environmentNameRef;
	protected final String rangeRef;
	protected final String targetValueRef;

	protected BasicGridModel environmentModel;
	protected int[] targetValues;
	protected int range;

	protected Value sensorValue;
	private int[][] offsets;
	private String varName;

	/**
	 * Attributes required to be present
	 * <ul>
	 * <li>environment - attribute reference for the environment</li>
	 * <li>range - attribute reference for the sensor range</li>
	 * <li>targetValue - attribute reference(s) for the target value for the
	 * sensor, in a CSV format</li>
	 * </ul>
	 * 
	 * <p>
	 * NB: the searching for multiple target values is not very optimized
	 * </p>
	 * 
	 * @param attributes
	 */
	public FilteredValueSensor( Map attributes )
	{
		super();

		if ( attributes.containsKey( "environment" ) == false )
		{
			throw new MissingAttributeError( "environment" );
		}

		if ( attributes.containsKey( "range" ) == false )
		{
			throw new MissingAttributeError( "range" );
		}

		if ( attributes.containsKey( "targetValue" ) == false )
		{
			throw new MissingAttributeError( "targetValue" );
		}

		this.environmentNameRef = ( String )attributes.get( "environment" );
		this.rangeRef = ( String )attributes.get( "range" );
		this.targetValueRef = ( String )attributes.get( "targetValue" );
		this.varName = ( String )attributes.get( "varName" );

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
		range = Integer.parseInt( ( String )model.query( rangeRef ) );

		StringTokenizer tok = new StringTokenizer( targetValueRef, "," );

		targetValues = new int[tok.countTokens()];

		for( int i = 0; tok.hasMoreTokens() && i < targetValues.length; i++ )
		{
			targetValues[i] = Integer.parseInt( ( String )model.query( tok.nextToken() ) );
		}

		// Put together the list of locations to search;
		List list = new ArrayList();
		list.add( new Point( 0, 0 ) );

		// XXX: later, use int[][] to collapse this big loop
		for( int i = 1; i <= range; i++ )
		{
			for( int d = -i + 1; d <= i - 1; d++ )
			{
				list.add( new Point( d, -i ) );
			}

			for( int d = -i + 1; d <= i - 1; d++ )
			{
				list.add( new Point( d, i ) );
			}

			for( int d = -i + 1; d <= i - 1; d++ )
			{
				list.add( new Point( -i, d ) );
			}

			for( int d = -i + 1; d <= i - 1; d++ )
			{
				list.add( new Point( i, d ) );
			}

			list.add( new Point( -i, -i ) );
			list.add( new Point( -i, i ) );
			list.add( new Point( i, -i ) );
			list.add( new Point( i, i ) );
		}

		Point[] searchLocations = ( Point[] )list.toArray( new Point[0] );
		offsets = new int[searchLocations.length][2];

		for( int i = 0; i < searchLocations.length; i++ )
		{
			offsets[i][0] = searchLocations[i].x;
			offsets[i][1] = searchLocations[i].y;
		}
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
		Point foundLoc = null;

		for( int i = 0; i < offsets.length; i++ )
		{
			int x = loc.x + offsets[i][0];
			int y = loc.y + offsets[i][1];
			int value = environmentModel.get( x, y );

			if ( search( value ) )
			{
				sensorValue = BooleanValue.TRUE;
				foundLoc = new Point( x, y );
				break;
			}
		}

		if ( varName != null )
		{
			State state = model.getState();
			state.put( varName, foundLoc );
		}
	}

	private boolean search( int val )
	{
		for( int x = 0; x < targetValues.length; x++ )
		{
			if ( targetValues[x] == val )
				return true;
		}

		return false;
	}
}