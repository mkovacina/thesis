package sweep.reference;

import java.util.Map;

import sweep.core.environment.Environment;
import sweep.model.AvatarModel;


/**
 * Description: EnvironmentReference
 * 
 * @author Michael A. Kovacina
 */
public class EnvironmentReference implements Reference
{
	private final String envName;
	private final String attrValue;

	/**
	 * @param envName
	 *            The name of the environment to query
	 * @param attrValue
	 *            The name of the attribute to retrieve
	 */
	public EnvironmentReference( String envName, String attrValue )
	{
		this.envName = envName;
		this.attrValue = attrValue;
	}

	/**
	 * @see sweep.reference.Reference#getValue(sweep.model.AvatarModel,
	 *      java.util.Map)
	 */
	public Object getValue( AvatarModel model, Map environmentMap )
	{
		Environment environment = ( Environment )environmentMap.get( envName );

		return environment.query( attrValue );
	}
}