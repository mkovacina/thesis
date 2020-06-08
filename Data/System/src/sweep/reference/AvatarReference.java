package sweep.reference;

import java.util.Map;

import sweep.model.AvatarModel;

/**
 * Description: AvatarReference
 * 
 * @author Michael A. Kovacina
 */
public class AvatarReference implements Reference
{
	private final String attrValue;
	
	/**
	 * @param attrValue
	 */
	public AvatarReference( String attrValue )
	{
		this.attrValue = attrValue;
	}

	/** 
	 * @see sweep.reference.Reference#getValue(sweep.model.AvatarModel, java.util.Map)
	 */
	public Object getValue( AvatarModel model, Map environmentMap )
	{
		return model.query(attrValue);
	}
}
