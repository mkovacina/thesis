/*
 * Created on Mar 11, 2004
 */
package sweep.environment;

import java.util.Map;


/**
 * @author orbital
 */
public class HelloWorldInitializer extends Initializer
{
	public HelloWorldInitializer( Map parameters )
	{
		super( parameters );

		// REFACTOR: make Initializer/Update an Action of model?
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sweep.model.Initializer#execute(sweep.model.SweepModel)
	 */
	public void execute( EnvironmentModel model )
	{
		System.out.println( "[/dev/null]# hello, world" );
	}
}