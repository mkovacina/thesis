/*
 * Created on Mar 11, 2004
 */

package sweep.model;

import java.util.Map;


/**
 * @author orbital
 */
public abstract class Update
{
	protected final Map parameters;

	/**
	 *  
	 */
	public Update( Map parameters )
	{
		this.parameters = parameters;
	}

	public abstract void execute( AvatarModel model, Map environmentMap );
}