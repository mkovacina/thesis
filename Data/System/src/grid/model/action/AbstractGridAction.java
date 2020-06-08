package grid.model.action;

import grid.environment.BasicGridModel;

import java.util.Map;

import sweep.core.environment.Environment;
import sweep.environment.SweepEnvironment;
import sweep.model.AvatarModel;
import sweep.model.action.AbstractAction;


/**
 * Description: AbstractGridAction is the base class for most grid-based
 * actions, providing basic funcationality needed by a grid-based actions.
 * 
 * @author Michael A. Kovacina
 */
public abstract class AbstractGridAction extends AbstractAction
{
	protected final String environmentReference;
	protected Environment environment;
	protected BasicGridModel gm;

	/**
	 * @param parameters - configuration parameters
	 */
	public AbstractGridAction( Map parameters )
	{
		super( parameters );
		this.environmentReference = (String)parameters.get("environment");
	}

	/**
	 * @param parameters - configuration parameters
	 * @param environmentReference - attribute reference for the environment name
	 */
	public AbstractGridAction( Map parameters, String environmentReference )
	{
		super( parameters );
		this.environmentReference = environmentReference;
	}

	/**
	 * @see sweep.model.action.AbstractAction#initialize(sweep.model.AvatarModel,
	 *      java.util.Map)
	 */
	public void initialize( AvatarModel model, Map environmentMap )
	{
		String environmentName = ( String )model.query( environmentReference );
		environment = ( Environment )environmentMap.get( environmentName );
		gm = ( BasicGridModel )( ( SweepEnvironment )environment ).getModel();
	}
}