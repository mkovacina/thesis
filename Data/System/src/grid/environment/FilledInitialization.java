/*
 * Created on May 10, 2004
 */
package grid.environment;

import java.util.Collections;
import java.util.Map;

import sweep.environment.EnvironmentModel;
import sweep.environment.Initializer;


/**
 * @author orbital
 */
public class FilledInitialization extends Initializer
{
	private final int x;
	private final int y;
	private final int width;
	private final int height;
	private final int value;

	/**
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param value
	 */
	public FilledInitialization( int x, int y, int w, int h, int value )
	{
		super( Collections.EMPTY_MAP );

		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
		this.value = value;
	}

	/**
	 * @param parameters
	 */
	public FilledInitialization( Map parameters )
	{
		super( parameters );
		throw new Error( "not implemented, not needed" );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sweep.environment.Initializer#execute(sweep.environment.EnvironmentModel)
	 */
	public void execute( EnvironmentModel model )
	{
		GridModel gm = ( GridModel )model;

		// XXX: need a unit test here...
		int xmax = Math.min( x + width, gm.getColumns() );
		int ymax = Math.min( y + height, gm.getRows() );

		for( int xx = x; xx < xmax; xx++ )
		{
			for( int yy = y; yy < ymax; yy++ )
			{
				gm.set( xx, yy, value );
			}
		}
	}
}