package sweep.controller;

import java.util.Iterator;
import java.util.Map;

import rule.AbstractRule;
import rule.Rule;
import sweep.core.agent.State;
import sweep.model.sensor.Value;


public class SensorRule extends AbstractRule
{
	protected final SensorValue[] targetSensorValues;
	private final int logical;
	private final static int AND = 0;
	private final static int OR = 1;

	protected SensorRule( Map sensors, String type )
	{
		super();

		if ( type.equalsIgnoreCase( "and" ) )
		{
			this.logical = AND;
		}
		else if ( type.equalsIgnoreCase( "or" ) )
		{
			this.logical = OR;
		}
		else
		{
			throw new Error( "Unknown logical operator: " + type );
		}

		int size = sensors.size();

		targetSensorValues = new SensorValue[size];

		Iterator iter = sensors.keySet().iterator();

		for( int x = 0; x < size && iter.hasNext(); x++ )
		{
			Object key = iter.next();

			targetSensorValues[x] = new SensorValue( ( String )key, ( String )sensors.get( key ) );
		}
	}

	/**
	 * @param targetSensorValues
	 * @param logical
	 */
	public SensorRule( SensorValue[] targetSensorValues, int logical )
	{
		this.logical = logical;
		this.targetSensorValues = targetSensorValues;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see act.rule.Rule#eval(java.util.Map)
	 */
	public boolean eval( Map input )
	{
		boolean result = false;

		switch( logical )
		{
			case OR:
				result = false;
				break;
			case AND:
				result = true;
				break;
		}

		State state = ( State )input.get( "state" );

		Map sensorValues = ( Map )state.get( "sensorValues" );

		//System.out.println("~~~~");
		for( int x = 0; x < targetSensorValues.length; x++ )
		{
			String name = targetSensorValues[x].name;

			Value value = ( Value )sensorValues.get( name );

			//System.out.println("-> ["+x+"] "+name+" = "+value);

			if ( value == null )
			{
				throw new Error( "Required sensor value '" + name + "' was not available" );
			}

			//boolean newResult = targetSensorValues[x].isMatched(value);
			boolean newResult = targetSensorValues[x].targetValue.equals( value );
			// REFACTOR: this needs to look better

			switch( logical )
			{
				case OR:
					result |= newResult;
					if ( result == true )
					{
						// satisfied the OR
						return true;
					}
					break;
				case AND:
					result &= newResult;
					if ( result == false )
					{
						// can't satisfy the AND
						return false;
					}
					break;
			}
		}

		return result;
	}

	public Rule copy()
	{
		return new SensorRule( targetSensorValues, logical );
	}
}