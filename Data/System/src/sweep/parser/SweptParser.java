package sweep.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.StringTokenizer;


/**
 * @author swarm
 * 
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates. To enable and disable the creation of type
 * comments go to Window>Preferences>Java>Code Generation.
 */
public class SweptParser
{
	BufferedReader reader;

	protected int columns;
	protected int rows;
	protected int numAgents;

	protected String[] agents;
	protected String[] environment;

	protected boolean endOfFile = false;

	// this is needed b/c the SWEEP animation file places an end-of-frame marker
	// after evey frame.
	// This includes the last frame, so after that marker comes the end-of-file
	// marker. This string acts
	// as a kind of pushback/lookahead
	protected String nextLine = "";

	public SweptParser( InputStream istream )
	{
		this( new InputStreamReader( istream ) );
	}

	public SweptParser( Reader r )
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
	}

	private void init() throws IOException
	{
		// TODO: is this a hack??
		String line_1 = reader.readLine();

		if ( line_1.startsWith( "version:" ) )
		{
			line_1 = reader.readLine();
		}

		String sizes = line_1 + " " + reader.readLine();
		StringTokenizer tok = new StringTokenizer( sizes );

		columns = Integer.parseInt( tok.nextToken() );
		rows = Integer.parseInt( tok.nextToken() );
		numAgents = Integer.parseInt( tok.nextToken() );

		agents = new String[numAgents];
		environment = new String[rows];

		//prepare the buffer
		nextLine = reader.readLine();
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

	public SweptFrame getNextFrame() throws IOException
	{
		readNextFrame();
		return getCurrentFrame();
	}

	public boolean readNextFrame() throws IOException
	{
		if ( endOfFile )
		{
			return true;
		}

		environment[0] = nextLine;

		for( int x = 1; x < columns; x++ )
		{
			environment[x] = reader.readLine();
		}

		for( int x = 0; x < numAgents; x++ )
		{
			agents[x] = reader.readLine();
		}

		// read end-of-frame marker
		reader.readLine();

		// get the lookahead buffer ready
		nextLine = reader.readLine();

		if ( nextLine.equals( "####" ) )
		{
			endOfFile = true;
		}

		return endOfFile;
	}

	public SweptFrame getCurrentFrame()
	{
		return new SweptFrame( environment, agents, endOfFile );
	}

	public boolean endOfFile()
	{
		return endOfFile;
	}
}