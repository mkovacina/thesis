package sweep.controller;

import sweep.model.sensor.AnyValue;
import sweep.model.sensor.BooleanValue;
import sweep.model.sensor.Value;


public class SensorValue
{
	public final String name;
	public final Value targetValue;
	public final boolean any;

	public SensorValue( String name, String value )
	{
		this.name = name;

		if ( value.equals( "true" ) )
		{
			this.targetValue = new BooleanValue( true );
			any = false;
		}
		else if ( value.equals( "false" ) )
		{
			this.targetValue = new BooleanValue( false );
			any = false;
		}
		else if ( value.equals( "any" ) )
		{
			this.targetValue = new AnyValue();
			any = true;
		}
		else
		{
			throw new Error( "bad sensor value: " + name + " = " + value );
		}
	}
}