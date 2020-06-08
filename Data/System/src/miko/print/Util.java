package miko.print;

import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class Util
{
	public static void print( Object[] array )
	{
		if ( array == null )
			return;

		for( int x = 0; x < array.length; x++ )
		{
			System.out.println( array[x] );
		}
	}

	public static void print( List list )
	{
		if ( list == null )
			return;

		for( int x = 0, size = list.size(); x < size; x++ )
		{
			System.out.println( list.get( x ) );
		}
	}

	public static void print( Map map )
	{
		if ( map == null )
			return;

		Iterator keys = map.keySet().iterator();

		while( keys.hasNext() )
		{
			Object key = keys.next();
			Object value = map.get( key );
			System.out.println( key + " => " + value );
		}
	}
}