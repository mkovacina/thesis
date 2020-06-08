package sweep.reference;

import java.util.Map;

import sweep.model.AvatarModel;


/**
 * Description: ConstantReference
 * 
 * @author Michael A. Kovacina
 */
public class ConstantReference implements Reference
{
	private final Object value;

	/**
	 * @param obj
	 */
	public ConstantReference( Object obj )
	{
		this.value = obj;
	}

	/**
	 * @see sweep.reference.Reference#getValue(sweep.model.AvatarModel,
	 *      java.util.Map)
	 */
	public Object getValue( AvatarModel model, Map environmentMap )
	{
		return value;
	}
}