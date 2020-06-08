package sweep.model.action;

import java.util.Map;

import sweep.model.AvatarModel;


/**
 * Description: SetStateVariableAction set the value of a variable in the agent's state
 *  
 * @author Michael A. Kovacina
 */
public class SetStateVariableAction extends AbstractAction
{
	private String varValue;
	private String varName;

	/**
	 * @param parameters
	 */
	public SetStateVariableAction( Map parameters )
	{
		super( parameters );
		this.varName = (String)parameters.get("flag");
		this.varValue = (String)parameters.get("value");
	}

	/**
	 * @see sweep.model.action.Action#invoke(sweep.model.AvatarModel,
	 *      java.util.Map)
	 */
	public void invoke( AvatarModel model, Map environmentMap )
	{
		model.getState().put(varName, varValue);
	}
}