/*
 * Created on May 10, 2004
 */
package grid.environment;

import java.util.Map;

import org.dom4j.Element;

import sweep.util.builder.Builder;


/**
 * @author orbital
 */
public class FilledInitializationBuilder extends Builder
{
	int x;
	int y;
	int w;
	int h;
	int value;

	/**
	 * @param containerElement
	 */
	public FilledInitializationBuilder( Element containerElement )
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
		Element rectElement = containerElement.element( "rect" );

		// XXX: need an automated way to check that all params exist, maybe some
		// kind of manifest xml file

		x = Integer.parseInt( rectElement.attributeValue( "top" ) );
		y = Integer.parseInt( rectElement.attributeValue( "left" ) );
		w = Integer.parseInt( rectElement.attributeValue( "width" ) );
		h = Integer.parseInt( rectElement.attributeValue( "height" ) );
		value = Integer.parseInt( containerElement.element( "object" ).attributeValue( "value" ) );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sweep.util.builder.Builder#create(java.util.Map)
	 */
	public Object create( Map parameters )
	{
		return new FilledInitialization( x, y, w, h, value );
	}
}