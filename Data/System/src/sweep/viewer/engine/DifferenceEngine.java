package sweep.viewer.engine;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.Reader;
import java.util.StringTokenizer;


public class DifferenceEngine extends SwarmSimEngine
{
	private Point[] agentPositions;
	private BufferedImage agentImage;
	private Graphics agentGraphics;

	public DifferenceEngine( Reader r )
	{
		super( r );

		agentPositions = new Point[numAgents];

		for( int x = 0; x < agentPositions.length; x++ )
		{
			agentPositions[x] = new Point( 0, 0 );
		}

		agentImage = new BufferedImage( width, height, BufferedImage.TYPE_INT_RGB );
		agentGraphics = agentImage.createGraphics();
	}

	protected void createNextImage() throws Exception
	{
		agentGraphics.setColor( Color.black );
		agentGraphics.fillRect( 0, 0, image.getWidth(), image.getHeight() );

		processEnvironmentSection();
		processAgentSection();
	}

	protected void processEnvironmentSection() throws Exception
	{
		for( String s = getLine().trim(); !s.equals( "~" ); s = getLine().trim() )
		{
			if ( showEnvironment )
			{
				StringTokenizer tok = new StringTokenizer( s );

				int lineNo = Integer.parseInt( tok.nextToken() );

				while( tok.hasMoreTokens() )
				{
					int pos = Integer.parseInt( tok.nextToken() );
					int val = Integer.parseInt( tok.nextToken() );

					if ( drawGrid )
					{
						graphics.setColor( Color.black );
						graphics.drawRect( lineNo * scale, pos * scale, scale, scale );

						graphics.setColor( environmentColorTable[val % environmentColorTable.length] );
						graphics.fillRect( ( lineNo - 1 ) * scale, ( pos - 1 ) * scale, scale - 1, scale - 1 );
					}
					else
					{
						graphics.fillRect( lineNo * scale, pos * scale, scale, scale );
					}
				}
			}
		}
	}

	protected void processAgentSection() throws Exception
	{
		for( int i = 0; i < numAgents; i++ )
		{
			String s = getLine();

			if ( showAgents )
			{
				StringTokenizer tok = new StringTokenizer( s );
				int x = Integer.parseInt( tok.nextToken() );
				int y = Integer.parseInt( tok.nextToken() );
				int color = Integer.parseInt( tok.nextToken() );

				agentPositions[i].x = x;
				agentPositions[i].y = y;

				if ( drawGrid )
				{
					agentGraphics.setColor( Color.black );
					agentGraphics.drawRect( x * scale, y * scale, scale, scale );

					agentGraphics.setColor( agentColorTable[color % agentColorTable.length] );
					agentGraphics.fillRect( ( x - 1 ) * scale, ( y - 1 ) * scale, scale - 1, scale - 1 );
				}
				else
				{
					agentGraphics.setColor( agentColorTable[color % agentColorTable.length] );
					agentGraphics.fillRect( x * scale, y * scale, scale, scale );
				}
			}
		}

		graphics.drawImage( agentImage, 0, 0, null );

		// eat end-of-frame
		getLine();
	}
}