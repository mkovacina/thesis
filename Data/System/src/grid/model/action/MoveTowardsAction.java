package grid.model.action;

import grid.model.GridAvatar;

import java.awt.Point;
import java.util.Map;

import sweep.model.AvatarModel;
import sweep.reference.Reference;
import sweep.reference.ReferenceFactory;


/**
 * Description: MoveTowardsAction moves the agent towards some specified point
 * by 1-unit
 * 
 * @author Michael A. Kovacina
 */
public class MoveTowardsAction extends AbstractGridAction
{
	private Reference locationReference;

	/**
	 * @param parameters
	 */
	public MoveTowardsAction( Map parameters )
	{
		super( parameters );

		this.locationReference = ReferenceFactory.createReference( ( String )parameters.get( "location" ) );
	}

	/**
	 * @see grid.model.action.AbstractGridAction#initialize(sweep.model.AvatarModel,
	 *      java.util.Map)
	 */
	public void initialize( AvatarModel model, Map environmentMap )
	{
		super.initialize( model, environmentMap );
	}

	/**
	 * @see sweep.model.action.Action#invoke(sweep.model.AvatarModel,
	 *      java.util.Map)
	 */
	public void invoke( AvatarModel model, Map environmentMap )
	{
		Point targetLocation = ( Point )locationReference.getValue( model, environmentMap );

		if ( targetLocation == null )
			return;

		Point modelLocation = ( ( GridAvatar )model ).getLocation();

		// XXX: debugger
		//System.out.println( "mt: " + targetLocation + " -- " + modelLocation );

		int offX = 0;
		int offY = 0;

		int dx = targetLocation.x - modelLocation.x;
		int dy = targetLocation.y - modelLocation.y;

		if ( dx == 0 && dy == 0 )
		{
			return;
		}

		if ( Math.abs( dx ) > Math.abs( dy ) )
		{
			if ( dx > 0 )
			{
				offX = 1;
			}
			else
			{
				offX = -1;
			}
		}
		else
		{
			if ( dy > 0 )
			{
				offY = 1;
			}
			else
			{
				offY = -1;
			}
		}

		( ( GridAvatar )model ).move( offX, offY );
	}
}