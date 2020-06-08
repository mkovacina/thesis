package grid.environment;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

import sweep.environment.EnvironmentModel;
import sweep.environment.Initializer;
import sweep.environment.Update;


/**
 * @author orbital
 */
public class BasicGridModel extends EnvironmentModel implements GridModel, LocationManager
{
	public static final int EMPTY_VALUE = 0;
	public static final int INVALID_VALUE = Integer.MIN_VALUE;

	protected int rows;
	protected int cols;
	protected int[][] grid;
	public int[][] agents;
	protected Map database;
	protected Point[] positions;
	public Map avatarPositions = new HashMap();

	/**
	 * @param initializers
	 * @param updates
	 * @param attributes
	 */
	public BasicGridModel( Initializer[] initializers, Update[] updates, Map attributes )
	{
		super( initializers, updates, attributes );

		if ( attributes.containsKey( "rows" ) == false )
		{
			throw new Error( "BasicGridModel: missing arguement for 'rows'" );
		}

		if ( attributes.containsKey( "cols" ) == false )
		{
			throw new Error( "BasicGridModel: missing arguement for 'cols'" );
		}

		rows = Integer.parseInt( ( String )attributes.get( "rows" ) );
		cols = Integer.parseInt( ( String )attributes.get( "cols" ) );

		grid = new int[rows][cols];
		agents = new int[rows][cols];

		database = new HashMap();
		database.put( "rows", Integer.toString( rows ) );
		database.put( "cols", Integer.toString( cols ) );
	}

	// Optimization: instead of converting the position map.values() everytime,
	// just do it when it is needed and track the changes
	// @see move()
	private boolean changed = true;
	private Point[] pts = new Point[0];

	public Object query( String queryString )
	{
		if ( "positions".equalsIgnoreCase( queryString ) )
		{
			if ( changed )
			{
				pts = ( Point[] )avatarPositions.values().toArray( pts );
				changed = false;
			}

			return pts;
		}

		if ( "grid".equalsIgnoreCase( queryString ) )
		{
			return grid;
		}

		return database.get( queryString );
	}

	public int get( Point p )
	{
		return grid[p.y][p.x];
	}

	public int get( int x, int y )
	{
		return grid[y][x];
	}

	public boolean set( int x, int y, int value )
	{
		grid[y][x] = value;
		return true;
	}

	public boolean set( Point p, int value )
	{
		grid[p.y][p.x] = value;
		return true;
	}

	public void clear( int x, int y )
	{
		grid[y][x] = EMPTY_VALUE;
	}

	public void clear( Point p )
	{
		grid[p.y][p.x] = EMPTY_VALUE;
	}

	public int remove( int x, int y )
	{
		int tmp = get( x, y );
		clear( x, y );
		return tmp;
	}

	public int remove( Point p )
	{
		int tmp = get( p );
		clear( p );
		return tmp;
	}

	public boolean isClear( int x, int y )
	{
		return grid[y][x] == EMPTY_VALUE;
	}

	public boolean isClear( Point p )
	{
		return grid[p.y][p.x] == EMPTY_VALUE;
	}

	public int getRows()
	{
		return rows;
	}

	public int getColumns()
	{
		return cols;
	}

	public boolean locationClear( Point p )
	{
		if ( p.x < 0 )
			return false;
		if ( p.x >= cols )
			return false;
		if ( p.y < 0 )
			return false;
		if ( p.y >= rows )
			return false;

		return agents[p.y][p.x] == 0;
	}

	public boolean locationClear( int x, int y )
	{
		if ( x < 0 )
			return false;
		if ( x >= cols )
			return false;
		if ( y < 0 )
			return false;
		if ( y >= rows )
			return false;

		return agents[y][x] == 0;
	}

	public boolean move( grid.model.GridAvatar gm, int dx, int dy )
	{
		Point p = gm.getLocation();

		avatarPositions.put( gm, p );
		agents[p.y][p.x] = 0;
		p.translate( dx, dy );
		agents[p.y][p.x] = 1;

		changed = true;

		return true;
	}
}