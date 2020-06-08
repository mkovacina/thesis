package grid.model.action;

import java.awt.Point;
import java.util.Map;

import sweep.core.agent.State;
import sweep.model.AvatarModel;


/**
 * Description: ConditionalReplaceAction replaces the grid value at the agent's
 * current location if it matches a target value
 * 
 * @author Michael A. Kovacina
 */
public class ConditionalReplaceAction extends AbstractGridAction
{
	private String targetReference;
	private String replaceReference;

	private int targetValue;
	private int replaceValue;

	/**
	 * @param parameters
	 */
	public ConditionalReplaceAction( Map parameters )
	{
		super( parameters );

		targetReference = ( String )parameters.get( "target" );
		replaceReference = ( String )parameters.get( "replace" );
	}

	/**
	 * @see sweep.model.action.Action#initialize(sweep.model.AvatarModel,
	 *      java.util.Map)
	 */
	public void initialize( AvatarModel model, Map environmentMap )
	{
		super.initialize( model, environmentMap );

		targetValue = Integer.parseInt( ( String )model.query( targetReference ) );
		replaceValue = Integer.parseInt( ( String )model.query( replaceReference ) );
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

		if ( currentValue == targetValue )
		{
			gm.set( p, replaceValue );
		}
	}
}