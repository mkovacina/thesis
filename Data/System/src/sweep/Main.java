package sweep;

import java.io.FileNotFoundException;

import org.dom4j.Document;
import org.dom4j.DocumentException;


public class Main
{
	public static void main( String[] args )
	{
		if ( args.length != 2 )
		{
			System.err.println( "Usage: java sweep.sim.Main <input.xml> <seed>" );
			return;
		}

		long randomSeed = Long.parseLong( args[1] );

		Document parameterDocument = null;

		try
		{
			parameterDocument = miko.xml.Util.createDocumentFromFile( args[0] );
		}
		catch( FileNotFoundException e )
		{
			e.printStackTrace();
			return;
		}
		catch( DocumentException e )
		{
			e.printStackTrace();
			return;
		}

		SWEEP sweep = new SWEEP( parameterDocument, randomSeed );
		sweep.run();
	}
}