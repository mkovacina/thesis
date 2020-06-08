/*
 * Created on Mar 14, 2004
 */

package sweep.model;

import java.util.Map;

import sweep.model.action.Action;


/**
 * @author orbital
 */
public class ModelUpdate extends Update
{
	/**
	 * @param parameters
	 */
	public ModelUpdate( Map parameters )
	{
		super( parameters );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sweep.avatar.Update#execute(sweep.avatar.AvatarModel)
	 */
	public void execute( AvatarModel model, Map environmentMap )
	{
		String[] command = model.connector.getCommands();

		for( int x = 0; x < command.length; x++ )
		{
			Action action = ( Action )model.actions.get( command[x] );
			action.invoke( model, environmentMap );
		}
	}
}