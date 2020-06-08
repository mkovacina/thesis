/*
 * Created on Mar 19, 2004
 */
package sweep.probe;

import java.awt.Point;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import sweep.core.probe.Probe;
import sweep.environment.SweepEnvironment;
import sweep.ice.ProbeConnector;


/**
 * @author orbital
 */
public class AnimationProbe implements Probe
{
	private String filename;
	private PrintWriter out;
	private int rows;
	private int cols;
	private ProbeConnector connector;

	private String environmentName;
	private SweepEnvironment environment;

	/**
	 *  
	 */
	public AnimationProbe( String filename, String environmentName, ProbeConnector connector )
	{
		super();

		this.filename = filename;
		this.environmentName = environmentName;
		this.connector = connector;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see epic.probe.Probe#initialize()
	 */
	public void initialize()
	{
		// TODO: ensure that initialize is only called once ???

		try
		{
			this.out = new PrintWriter( new FileWriter( filename ) );
		}
		catch( IOException e )
		{
			throw new Error( "Could not create file '" + filename + "' in AnimationProbe." );
		}

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see epic.probe.Probe#update()
	 */
	public boolean update()
	{
		Point[] positions = ( Point[] )environment.query( "positions" );
		int[][] grid = ( int[][] )environment.query( "grid" );

		for( int r = 0; r < grid.length; r++ )
		{
			for( int c = 0; c < grid[r].length; c++ )
			{
				out.print( grid[r][c] );
			}

			out.println();
		}

		for( int x = 0; x < positions.length; x++ )
		{
			out.print( positions[x].x );
			out.print( " " );
			out.print( positions[x].y );
			out.println( " 0" );
		}

		out.println( "*" );

		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see epic.probe.Probe#destroy()
	 */
	public void destroy()
	{
		out.println( "####" );
		out.close();
	}
}