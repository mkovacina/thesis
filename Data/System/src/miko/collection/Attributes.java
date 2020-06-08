package miko.collection;

import java.util.HashMap;
import java.util.List;


/**
 * Description: Attributes is basically a String-based mapping for getting and
 * setting collections of properties. This should eventually be replaced by a
 * template Map, maybe.
 * 
 * @author Michael A. Kovacina
 */
public class Attributes extends HashMap
{
	public List contains( String[] keys )
	{
		return null;
	}

	/**
	 * @param key
	 * @return the value associated with key
	 */
	public String get( String key )
	{
		return ( String )super.get( key );
	}

	/**
	 * @param key
	 * @param defaultValue
	 * @return the value associated with key, or defaultValue if null
	 */
	public String get( String key, String defaultValue )
	{
		String value = ( String )super.get( key );

		return value == null ? defaultValue : value;
	}

	/**
	 * @param key
	 * @param value
	 */
	public void set( String key, String value )
	{
		this.put( key, value );
	}
}