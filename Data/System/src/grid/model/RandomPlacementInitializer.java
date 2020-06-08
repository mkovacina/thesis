/*
 * Created on Mar 17, 2004
 */
package grid.model;

import java.util.Map;

import miko.math.StaticRandom;
import sweep.model.AvatarModel;
import sweep.model.Initializer;


/**
 * @author orbital
 */
public class RandomPlacementInitializer extends Initializer
{
	/**
	 * @param parameters
	 */
	public RandomPlacementInitializer( Map parameters )
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

		int r = StaticRandom.nextInt( rows );
		int c = StaticRandom.nextInt( cols );

		gridModel.setLocation( c, r );
	}
}