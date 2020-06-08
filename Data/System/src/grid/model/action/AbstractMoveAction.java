package grid.model.action;

import grid.model.GridAvatar;

import java.awt.Point;
import java.util.Map;

import sweep.core.agent.State;
import sweep.model.AvatarModel;


/**
 * Description: AbstractMoveAction is the base class for all movement actions
 * for the grid environment.
 * 
 * @author Michael A. Kovacina
 */
public abstract class AbstractMoveAction extends AbstractGridAction
{
	protected int MIN_X;
	protected int MAX_X;
	protected int MIN_Y;
	protected int MAX_Y;

	protected final int offset_X;
	protected final int offset_Y;

	/**
	 * @param parameters -
	 *            configurations parameters
	 * @param environmentReference -
	 *            attribute reference to the target environment
	 * @param offset_X -
	 *            magnitude of the x-direction move
	 * @param offset_Y -
	 *            magnitude of the y-direction move
	 */
	public AbstractMoveAction( Map parameters, String environmentReference, int offset_X, int offset_Y )
	{
		super( parameters, environmentReference );

		this.offset_X = offset_X;
		this.offset_Y = offset_Y;
	}

	/**
	 * @see sweep.model.action.Action#invoke(sweep.model.AvatarModel,
	 *      java.util.Map)
	 */
	public void invoke( AvatarModel model, Map environmentMap )
	{
		State state = model.getState();
		Point p = ( Point )state.get( "position" );

		if ( checkValidity( p, offset_X, offset_Y ) == true )
		{
			( ( GridAvatar )model ).move( offset_X, offset_Y );
		}
	}

	protected boolean checkValidity( Point p, int xOff, int yOff )
	{
		int x = p.x + xOff;
		int y = p.y + yOff;

		return gm.locationClear( x, y );
	}
}