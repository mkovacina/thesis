package grid.model.action;

import java.awt.Point;
import java.util.Map;

import sweep.core.agent.State;
import sweep.model.AvatarModel;


/**
 * Description: SetAction sets the value of the grid location of the
 * agent to the specified value
 * 
 * @author Michael A. Kovacina
 */
public class SetAction extends AbstractGridAction
{
	private String valueReference;

	private int value;

	/**
	 * @param parameters
	 */
	public SetAction( Map parameters )
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

		value = Integer.parseInt( ( String )model.query( valueReference ) );
	}

	/**
	 * @see sweep.model.action.Action#invoke(sweep.model.AvatarModel,
	 *      java.util.Map)
	 */
	public void invoke( AvatarModel model, Map environmentMap )
	{
		State state = model.getState();
		Point p = ( Point )state.get( "position" );
		// XXX: doh, this needs to be done b/c of the safegrid, so fix this
		gm.clear(p);
		gm.set( p, value );
	}
}