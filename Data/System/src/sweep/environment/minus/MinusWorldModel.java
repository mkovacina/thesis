/*
 * Created on Mar 11, 2004
 */

package sweep.environment.minus;

import java.util.Map;

import sweep.environment.EnvironmentModel;
import sweep.environment.Initializer;
import sweep.environment.Update;


/**
 * @author orbital
 */
public class MinusWorldModel extends EnvironmentModel
{
	/**
	 * @param initializers
	 * @param updates
	 * @param attributes
	 */
	public MinusWorldModel( Initializer[] initializers, Update[] updates, Map attributes )
	{
		super( initializers, updates, attributes );
	}
}