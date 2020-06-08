package sweep.model.action;

import java.util.Map;

import sweep.model.AvatarModel;


/**
 * Description: DisableAction disables the agent, preventing it from executing
 * any other instructions until either the end of the sim or until the flag is
 * reset.
 * 
 * @author Michael A. Kovacina
 */
public class DisableAction extends AbstractAction
{
	/**
	 * @param parameters
	 */
	public DisableAction( Map parameters )
	{
		super( parameters );
	}

	/**
	 * @see sweep.model.action.Action#invoke(sweep.model.AvatarModel,
	 *      java.util.Map)
	 */
	public void invoke( AvatarModel model, Map environmentMap )
	{
		model.getState().put( "active", Boolean.FALSE );
	}
}