/*
 * Created on Jul 6, 2003
 * 
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package sweep.parser;

import java.awt.Point;
import java.util.StringTokenizer;


/**
 * @author razor
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SweptFrame
{
	public final char[][] environment;
	public final Point[] agents;
	public final int[] color;
	public boolean isLastFrame = false;

	public SweptFrame( int rows, int cols, int numAgents )
	{
		environment = new char[rows][cols];
		agents = new Point[numAgents];
		color = new int[numAgents];

		for( int x = 0; x < environment.length; x++ )
		{
			for( int y = 0; y < environment[x].length; y++ )
			{
				environment[x][y] = '0';
			}
		}

		for( int x = 0; x < agents.length; x++ )
		{
			agents[x] = new Point();
		}
	}

	public SweptFrame( String[] env, String[] agt, boolean lastFrame )
	{
		this.isLastFrame = lastFrame;

		environment = new char[env.length][env[0].length()];
		agents = new Point[agt.length];
		color = new int[agt.length];

		for( int r = 0; r < environment.length; r++ )
		{
			environment[r] = env[r].toCharArray();
		}

		for( int x = 0; x < agents.length; x++ )
		{
			StringTokenizer tok = new StringTokenizer( agt[x] );

			agents[x] = new Point( Integer.parseInt( tok.nextToken() ), Integer.parseInt( tok.nextToken() ) );
			color[x] = Integer.parseInt( tok.nextToken() );
		}
	}

	public boolean isLastFrame()
	{
		return isLastFrame;
	}
}