/*
 * Created on Mar 19, 2004
 */

package sweep.probe;

import java.awt.Point;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import sweep.avatar.SweepAvatar;
import sweep.core.probe.Probe;
import sweep.environment.SweepEnvironment;
import sweep.ice.ProbeConnector;


/**
 * @author orbital
 */
public class NetworkAnimationProbe implements Probe
{
	private OutputStream ostream;
	private PrintWriter out;
	private int rows;
	private int cols;
	private ProbeConnector connector;

	private String environmentName;
	private SweepEnvironment environment;

	/**
	 * @param ostream
	 * @param environmentName
	 * @param connector
	 *  
	 */
	public NetworkAnimationProbe( OutputStream ostream, String environmentName, ProbeConnector connector )
	{
		super();

		this.ostream = ostream;
		this.environmentName = environmentName;
		this.connector = connector;
	}

	/**
	 * @see sweep.core.probe.Probe#initialize()
	 */
	public void initialize()
	{
		// TODO: ensure that initialize is only called once ???

		this.out = new PrintWriter( new OutputStreamWriter( ostream ) );

		environment = ( SweepEnvironment )connector.environmentMap.get( environmentName );

		rows = Integer.parseInt( ( String )environment.query( "rows" ) );
		cols = Integer.parseInt( ( String )environment.query( "cols" ) );

		//(1) the version of the file
		out.println( "version: Swept" );

		// (2) size of the grid
		out.println( rows + " " + cols );

		// (3) number of agents
		out.println( connector.agents.size() );
	}

	private SweepAvatar[] avatars = new SweepAvatar[0];

	/**
	 * @see sweep.core.probe.Probe#update()
	 */
	public boolean update()
	{
		int[][] grid = ( int[][] )environment.query( "grid" );

		for( int r = 0; r < grid.length; r++ )
		{
			for( int c = 0; c < grid[r].length; c++ )
			{
				// make sure that the value is in [0,9]
				int value = grid[c][r] % 9;

				out.print( value );
			}

			out.println();
		}

		avatars = ( SweepAvatar[] )connector.avatars.toArray( avatars );

		for( int x = 0; x < avatars.length; x++ )
		{
			Point p = ( Point )avatars[x].query( "position" );
			out.print( p.x );
			out.print( " " );
			out.print( p.y );
			out.println( " 0" );
		}

		/*
		 * Point[] positions = ( Point[] )environment.query( "positions" ); for(
		 * int x = 0; x < positions.length; x++ ) { out.print( positions[x].x );
		 * out.print( " " ); out.print( positions[x].y ); out.println( " 0" ); }
		 */
		out.println( "*" );

		return true;
	}

	/**
	 * @see sweep.core.probe.Probe#destroy()
	 */
	public void destroy()
	{
		out.println( "####" );
		out.close();
	}
}