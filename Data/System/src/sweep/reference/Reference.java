package sweep.reference;

import java.util.Map;

import sweep.model.AvatarModel;


/**
 * Description: Reference Interface for all SWEEP references. The purpose of the
 * reference is to abstract where and how variable information is coming from.
 * For example, are you querying an avatar, an environment, or just some
 * constant value? This interface should help facilitate avoing the need to
 * write specific classes to handle the different instances.
 * 
 * @author Michael A. Kovacina
 */
public interface Reference
{
	/**
	 * @param model The avatar model that may be queried
	 * @param environmentMap The mapping of all environments that may be queried
	 * @return The value of the reference
	 */
	Object getValue( AvatarModel model, Map environmentMap );
}