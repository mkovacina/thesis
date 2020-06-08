/*
 * Created on Jan 30, 2004
 */
package sweep.util.factory;

import java.util.Map;

import sweep.util.builder.Builder;


/**
 * @author orbital
 */
public class Factory
{
	final private Map builders;

	/**
	 * @param builders
	 */
	public Factory( final Map builders )
	{
		this.builders = builders;
	}

	public Object create( String type, Map parameters )
	{
		if ( builders.containsKey( type ) == false )
		{
			System.err.println( builders.keySet() );
			throw new Error( "Builder could not find declaration of type: " + type );
		}

		Builder builder = ( Builder )builders.get( type );
		return builder.create( parameters );
	}
}