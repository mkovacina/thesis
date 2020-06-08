package sweep.model.action;

import java.util.Map;

import sweep.model.AvatarModel;


/**
 * Description: NoOpAction is the most worthless action ever...it does NOTHING!
 * 
 * @author Michael A. Kovacina
 */
public class NoOpAction extends AbstractAction
{
	/**
	 * @param attributes
	 */
	public NoOpAction( Map attributes )
	{
		super( attributes );
	}

	/**
	 * That's right, this does <b>NOTHING!</b>
	 *  
	 * @see sweep.model.action.Action#invoke(sweep.model.AvatarModel, java.util.Map)
	 */
	public void invoke( AvatarModel model, Map environmentMap )
	{
	}
}