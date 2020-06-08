/*
 * Created on Mar 26, 2004
 * 
 * To change the template for this generated file go to Window - Preferences -
 * Java - Code Generation - Code and Comments
 */
package grid.environment;

import java.util.List;
import java.util.Map;

import org.dom4j.Element;

import sweep.util.builder.Builder;


/**
 */
public class ExplicitInitializationBuilder extends Builder
{
	int[][] data;

	/**
	 * @param containerElement
	 */
	public ExplicitInitializationBuilder( Element containerElement )
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
		List dataElements = containerElement.elements( "data" );

		data = new int[dataElements.size()][3];

		for( int i = 0; i < dataElements.size(); i++ )
		{
			Element dataElement = ( Element )dataElements.get( i );

			data[i][0] = Integer.parseInt( dataElement.attributeValue( "x" ) );
			data[i][1] = Integer.parseInt( dataElement.attributeValue( "y" ) );
			data[i][2] = Integer.parseInt( dataElement.attributeValue( "value" ) );
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sweep.util.builder.Builder#create(java.util.Map)
	 */
	public Object create( Map parameters )
	{
		return new ExplicitInitialization( data );
	}
}