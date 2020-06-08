package sweep.model.action;

import java.util.Map;

import sweep.core.agent.State;
import sweep.model.AvatarModel;


/**
 * Description: IncrementCountAction increases a state variable by one
 *  
 * @author Michael A. Kovacina
 */
public class IncrementCounterAction extends AbstractAction
{
	private String varName;

	/**
	 * @param parameters
	 */
	public IncrementCounterAction( Map parameters )
	{
		super( parameters );
		this.varName = (String)parameters.get("name");
	}

	/** 
	 * @see sweep.model.action.Action#initialize(sweep.model.AvatarModel, java.util.Map)
	 */
	public void initialize( AvatarModel model, Map environmentMap )
	{
		super.initialize( model, environmentMap );
		
		model.getState().put(varName, new Integer(0));
	}
	
	/**
	 * @see sweep.model.action.Action#invoke(sweep.model.AvatarModel,
	 *      java.util.Map)
	 */
	public void invoke( AvatarModel model, Map environmentMap )
	{
		// XXX: autoboxing here
		State state = model.getState();
		Integer val = ( Integer )state.get(varName);
		model.getState().put(varName, new Integer(val.intValue()+1) );
	}
}