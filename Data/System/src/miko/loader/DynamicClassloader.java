package miko.loader;

import java.lang.reflect.Constructor;


public class DynamicClassloader
{
	public static Object newInstance( final String className, final Class[] formalParameters,
			final Object[] actualParameters )
	{
		Object instance = null;

		try
		{
			Class c = Class.forName( className );
			Constructor constructor = c.getConstructor( formalParameters );
			instance = constructor.newInstance( actualParameters );
		}
		catch( Throwable t )
		{
			/**
			 * This works. If you give me a reason to enumerate every damn
			 * exception thrown by these three lines of code, and good reason at
			 * that, then I'll gladly do it.
			 */
			t.printStackTrace();
			System.err.println( "[ERROR]: Could not find the specified constructor" );
			System.err.println( "  -> classname: " + className );
			System.err.println( "  -> parameters:" );
			for( int x = 0; x < formalParameters.length; x++ )
			{
				System.err.println( "  ---> " + formalParameters[x] );
			}
			System.exit( 1 );
		}

		return instance;
	}

	/* just a convenience method for loading classes with empty constructors */
	public static Object newInstance( final String classname )
	{
		return newInstance( classname, new Class[0], new Object[0] );
	}
}