/*
 * Created on Jul 6, 2003
 * 
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package sweep.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.StringTokenizer;


/**
 * @author razor
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SweptCompressedParser
{
	private SweptFrame frame;

	BufferedReader reader;

	protected int columns;
	protected int rows;
	protected int numAgents;

	protected boolean endOfFile = false;

	/**
	 * @param istream
	 */
	public SweptCompressedParser( InputStream istream )
	{
		this( new InputStreamReader( istream ) );
	}

	/**
	 * @param r
	 */
	public SweptCompressedParser( Reader r )
	{
		reader = new BufferedReader( r, 100 );

		try
		{
			init();
		}
		catch( IOException e )
		{
			new Error( "[ERROR] Problem parsing SWEPT file header." );
		}

		frame = new SweptFrame( rows, columns, numAgents );
	}

	private void init() throws IOException
	{
		String sizes = reader.readLine() + " " + reader.readLine();
		StringTokenizer tok = new StringTokenizer( sizes );

		columns = Integer.parseInt( tok.nextToken() );
		rows = Integer.parseInt( tok.nextToken() );
		numAgents = Integer.parseInt( tok.nextToken() );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sweep.parser.SweptParser#getNextFrame()
	 */
	public SweptFrame getNextFrame() throws IOException
	{
		if ( endOfFile )
		{
			return frame;
		}

		String line = reader.readLine();

		if ( line.equals( ">>" ) == false )
		{
			endOfFile = true;
			frame.isLastFrame = true;
			return frame;
		}

		while( ( line = reader.readLine() ).equals( "~~~" ) == false )
		{
			StringTokenizer tok = new StringTokenizer( line );
			int x = Integer.parseInt( tok.nextToken() );
			int y = Integer.parseInt( tok.nextToken() );
			char c = tok.nextToken().charAt( 0 );

			frame.environment[x][y] = c;
		}

		for( int x = 0; x < numAgents; x++ )
		{
			StringTokenizer tok = new StringTokenizer( reader.readLine() );

			frame.agents[x].x = Integer.parseInt( tok.nextToken() );
			frame.agents[x].y = Integer.parseInt( tok.nextToken() );
			frame.color[x] = Integer.parseInt( tok.nextToken() );
		}

		// read end-of-frame marker
		reader.readLine();

		return frame;
	}

	public int getNumberOfAgents()
	{
		return numAgents;
	}

	public int getNumberOfRows()
	{
		return rows;
	}

	public int getNumberOfColumns()
	{
		return columns;
	}
}