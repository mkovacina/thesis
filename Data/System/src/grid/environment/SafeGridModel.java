package grid.environment;

import java.awt.Point;
import java.util.Map;

import sweep.environment.Initializer;
import sweep.environment.Update;


public class SafeGridModel extends BasicGridModel
{
	/**
	 * @param initializers
	 * @param updates
	 * @param attributes
	 */
	public SafeGridModel( Initializer[] initializers, Update[] updates, Map attributes )
	{
		super( initializers, updates, attributes );
	}

	protected boolean isValidLocation( int x, int y )
	{
		return x >= 0 && y >= 0 && x < cols && y < rows;
	}

	protected boolean isValidLocation( Point p )
	{
		return p.x >= 0 && p.y >= 0 && p.x < cols && p.y < rows;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see grid.environment.GridModel#get(int, int)
	 */
	public int get( int x, int y )
	{
		int value = INVALID_VALUE;

		if ( isValidLocation( x, y ) )
		{
			value = super.get( x, y );
		}

		return value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see grid.environment.GridModel#get(java.awt.Point)
	 */
	public int get( Point p )
	{
		int value = INVALID_VALUE;

		if ( isValidLocation( p ) )
		{
			value = super.get( p );
		}

		return value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see grid.environment.GridModel#set(int, int, int)
	 */
	public boolean set( int x, int y, int value )
	{
		boolean result = false;

		if ( isValidLocation( x, y ) && super.isClear( x, y ) )
		{
			result = super.set( x, y, value );
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see grid.environment.GridModel#set(java.awt.Point, int)
	 */
	public boolean set( Point p, int value )
	{
		boolean result = false;

		if ( isValidLocation( p ) && super.isClear( p.x, p.y ) )
		{
			result = super.set( p, value );
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see grid.environment.GridModel#clear(int, int)
	 */
	public void clear( int x, int y )
	{
		if ( isValidLocation( x, y ) )
		{
			super.clear( x, y );
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see grid.environment.GridModel#clear(java.awt.Point)
	 */
	public void clear( Point p )
	{
		if ( isValidLocation( p ) )
		{
			super.clear( p );
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see grid.environment.GridModel#remove(int, int)
	 */
	public int remove( int x, int y )
	{
		int value = INVALID_VALUE;

		if ( isValidLocation( x, y ) )
		{
			value = super.remove( x, y );
		}
		return value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see grid.environment.GridModel#remove(java.awt.Point)
	 */
	public int remove( Point p )
	{
		int value = INVALID_VALUE;

		if ( isValidLocation( p ) )
		{
			value = super.remove( p );
		}

		return value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see grid.environment.GridModel#isClear(int, int)
	 */
	public boolean isClear( int x, int y )
	{
		boolean result = false;

		if ( isValidLocation( x, y ) )
		{
			result = super.isClear( x, y );
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see grid.environment.GridModel#isClear(java.awt.Point)
	 */
	public boolean isClear( Point p )
	{
		boolean result = false;

		if ( isValidLocation( p ) )
		{
			result = super.isClear( p );
		}

		return result;
	}
}