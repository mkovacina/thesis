/*
 * Created on Mar 17, 2004
 */
package grid.model;

import java.util.Map;

import sweep.model.AvatarModel;
import sweep.model.Initializer;


/**
 * @author orbital
 */
public class LinearPlacementInitializer extends Initializer
{
	private int currX = 0;
	private int currY = 0;

	/**
	 * @param parameters
	 */
	public LinearPlacementInitializer( Map parameters )
	{
		super( parameters );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sweep.model.Initializer#execute(sweep.model.AvatarModel)
	 */
	public void execute( AvatarModel model, Map environmentMap )
	{
		GridAvatar gridModel = ( GridAvatar )model;

		int cols = gridModel.cols;
		int rows = gridModel.rows;

		gridModel.setLocation( currX, currY );

		currX = ( currX + 1 ) % cols;

		if ( currX == 0 )
		{
			currY = ( currY + 1 ) % rows;
		}
	}
}