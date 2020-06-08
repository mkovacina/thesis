/*
 * Created on Jan 30, 2004
 */
package sweep.core.agent;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


/**
 * @author orbital
 */
public class State
{
	final private Map map;

	public State()
	{
		map = new HashMap();
	}

	public Object get( String key )
	{
		return map.get( key );
	}

	public void put( String key, Object value )
	{
		map.put( key, value );
	}
	
	public void putAll(Map newMap)
	{
		map.putAll(newMap);
	}

	public String toString()
	{
		StringBuffer buf = new StringBuffer( 100 );
		buf.append( "[" );
		Iterator keys = map.keySet().iterator();

		while( keys.hasNext() )
		{
			Object key = keys.next();
			Object value = map.get( key );
			buf.append( "<" );
			buf.append( key );
			buf.append( "," );
			buf.append( value );
			buf.append( ">" );
		}

		buf.append( "]" );

		return buf.toString();
	}
}