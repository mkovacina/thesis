package sweep.viewer.engine;

import java.io.BufferedReader;
import java.io.Reader;
import java.util.StringTokenizer;

import miko.loader.DynamicClassloader;


public class EngineBuilder
{
	private static final String HEADER_TAG = "version:";

	protected EngineBuilder()
	{
	}

	public static Engine createEngine( Reader reader )
	{
		BufferedReader bufferedReader = new BufferedReader( reader );

		String firstLine = "";

		try
		{
			firstLine = bufferedReader.readLine().trim();
		}
		catch( Exception e )
		{
			System.err.println( "[ERROR] I/O error." );
			System.exit( 0 );
		}

		Engine engine = null;

		if ( firstLine.startsWith( HEADER_TAG ) )
		{
			Class[] formalParameters = new Class[] {Reader.class };
			Object[] actualParameters = new Object[] {bufferedReader };
			String classname = "sweep.viewer.engine." + firstLine.substring( HEADER_TAG.length() ).trim() + "Engine";
			engine = ( Engine )DynamicClassloader.newInstance( classname, formalParameters, actualParameters );
		}
		else
		{
			StringTokenizer tok = new StringTokenizer( firstLine );

			int rows = 0;
			int cols = 0;
			int numAgents = 0;

			try
			{
				rows = Integer.parseInt( tok.nextToken() );
				cols = Integer.parseInt( tok.nextToken() );
				numAgents = Integer.parseInt( bufferedReader.readLine() );
			}
			catch( Exception e )
			{
				System.err.println( "[ERROR] Bad row/col format." );
				System.exit( 0 );
			}

			//viewer = new SwarmSimViewer( reader, rows, cols, numAgents );

			engine = new SwarmSimEngine( bufferedReader, rows, cols, numAgents );

			//engine = new SwarmSimEngine( bufferedReader, firstLine );
		}

		return engine;
	}
}