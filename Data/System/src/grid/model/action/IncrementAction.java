package grid.model.action;

import java.awt.Point;
import java.util.Map;

import sweep.core.agent.State;
import sweep.model.AvatarModel;


/**
 * Description: IncrementAction increments by the value grid location of the
 * agent
 * 
 * @author Michael A. Kovacina
 */
public class IncrementAction extends AbstractGridAction
{
	private String valueReference;

	private int incrementValue;

	/**
	 * @param parameters
	 */
	public IncrementAction( Map parameters )
	{
		super( parameters );

		valueReference = ( String )parameters.get( "value" );
	}

	/**
	 * @see sweep.model.action.Action#initialize(sweep.model.AvatarModel,
	 *      java.util.Map)
	 */
	public void initialize( AvatarModel model, Map environmentMap )
	{
		super.initialize( model, environmentMap );

		incrementValue = Integer.parseInt( ( String )model.query( valueReference ) );
	}

	/**
	 * @see sweep.model.action.Action#invoke(sweep.model.AvatarModel,
	 *      java.util.Map)
	 */
	public void invoke( AvatarModel model, Map environmentMap )
	{
		State state = model.getState();
		Point p = ( Point )state.get( "position" );

		int currentValue = gm.get( p );
		gm.set( p, currentValue + incrementValue );
	}
}