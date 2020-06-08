/*
 * Created on Jan 30, 2004
 */
package sweep.model.sensor;

import java.util.HashMap;
import java.util.Map;


/**
 * @author orbital
 */
public class SensorMap
{
	private Map map;

	public SensorMap()
	{
		map = new HashMap();
	}

	public void put( String key, Value value )
	{
		map.put( key, value );
	}

	public Value get( String key )
	{
		return ( Value )map.get( key );
	}
}