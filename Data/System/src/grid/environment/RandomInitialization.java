package grid.environment;

import java.util.Collections;
import java.util.Map;
import java.util.Random;

import sweep.environment.EnvironmentModel;
import sweep.environment.Initializer;


/**
 * Description: RandomInitialization fills the grid environment with a specified
 * of objects. The initialization can take a seed value, thus making it easy to
 * reproduce random environment independently of the random seed specified for
 * the entire simulation.
 * 
 * @author Michael A. Kovacina
 */
public class RandomInitialization extends Initializer
{
	private int[][] objects;
	private long seed;

	public RandomInitialization( Map parameters )
	{
		super( parameters );
	}

	public RandomInitialization( int[][] objects, long seed )
	{
		super( Collections.EMPTY_MAP );

		this.seed = seed;
		this.objects = ( int[][] )objects.clone();
	}

	public void execute( EnvironmentModel model )
	{
		Random rng = new Random( seed );

		GridModel gm = ( GridModel )model;
		int numberOfObjects = objects.length;

		int rows = gm.getRows();
		int cols = gm.getColumns();

		for( int i = 0; i < numberOfObjects; i++ )
		{
			int value = objects[i][0];
			int numberOfValues = objects[i][1];

			for( int j = 0; j < numberOfValues; j++ )
			{
				int x = 0;
				int y = 0;

				// be sure not to overwrite
				do
				{
					x = rng.nextInt( cols );
					y = rng.nextInt( rows );
				}
				while( gm.isClear( x, y ) == false );

				gm.set( x, y, value );
			}
		}
	}
}