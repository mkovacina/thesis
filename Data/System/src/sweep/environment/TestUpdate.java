/*
 * Created on Mar 11, 2004
 */
package sweep.environment;

import java.util.Map;


/**
 * @author orbital
 */
public class TestUpdate extends Update
{
	/**
	 *  
	 */
	public TestUpdate( Map parameters )
	{
		super( parameters );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sweep.model.Update#execute(sweep.model.SweepModel)
	 */
	public void execute()
	{
		System.err.println( "...ping..." );
	}
}