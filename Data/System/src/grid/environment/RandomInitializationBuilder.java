package grid.environment;

import java.util.List;
import java.util.Map;
import java.util.Random;

import org.dom4j.Element;

import sweep.util.builder.Builder;


/**
 * Description: RandomInitializationBuilder is the builder class that handles
 * the construction of RandomInitialization objects.
 * 
 * @author Michael A. Kovacina
 */
public class RandomInitializationBuilder extends Builder
{
	private int[][] objects;
	private long seed;

	/**
	 * @param containerElement
	 */
	public RandomInitializationBuilder( Element containerElement )
	{
		super( containerElement );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sweep.util.builder.Builder#initialize()
	 */
	protected void initialize()
	{
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sweep.util.builder.Builder#process(org.dom4j.Element)
	 */
	protected void process( Element containerElement )
	{
		Element seedElement = containerElement.element( "seed" );

		if ( seedElement == null )
		{
			seed = new Random().nextLong();
		}
		else
		{
			seed = Long.parseLong( seedElement.attributeValue( "value" ) );
		}
		List objectElements = containerElement.elements( "object" );

		objects = new int[objectElements.size()][2];

		for( int i = 0; i < objectElements.size(); i++ )
		{
			Element dataElement = ( Element )objectElements.get( i );

			objects[i][0] = Integer.parseInt( dataElement.attributeValue( "value" ) );
			objects[i][1] = Integer.parseInt( dataElement.attributeValue( "number" ) );
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sweep.util.builder.Builder#create(java.util.Map)
	 */
	public Object create( Map parameters )
	{
		return new RandomInitialization( objects, seed );
	}
}