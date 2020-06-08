package sweep.model.action;

import java.util.Map;

import sweep.model.AvatarModel;


public abstract class AbstractAction implements Action
{
	public AbstractAction( Map parameters )
	{
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sweep.model.action.Action#initialize(sweep.model.AvatarModel,
	 *      java.util.Map)
	 */
	public void initialize( AvatarModel model, Map environmentMap )
	{
	}
}