package standard.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import miko.loader.DynamicClassloader;

import org.dom4j.Element;

import sweep.util.builder.Builder;


/**
 * Description: Util A utility class useful when working with dom4j and XML
 * 
 * @author Michael A. Kovacina
 */
public final class Util
{
	/**
	 *  
	 */
	private Util()
	{
		super();
	}

	/**
	 * @param attributesElement
	 *            Collection of attribute description elements
	 * @return The collection of constructed attributes
	 */
	public static final Map createAttributeMap( Element attributesElement )
	{
		return miko.xml.Util.createMap( attributesElement, "attribute", "name", "value" );
	}

	/**
	 * @param containerElement
	 *            The element containing subelements that have either class or
	 *            builder attributes indicating how they should be instantiated
	 * @return A mapping of names to constructed objects
	 */
	public static final Map createMap( Element containerElement )
	{
		Map map = new HashMap();

		Iterator elementIterator = containerElement.elementIterator();

		while( elementIterator.hasNext() )
		{
			Element element = ( Element )elementIterator.next();

			Object object = null;
			String name = element.attributeValue( "name" );

			String builderClazz = element.attributeValue( "builder" );

			if ( builderClazz != null )
			{
				Class[] formalParameters = new Class[] {Element.class };
				Object[] actualParameters = new Object[] {element };
				Builder builder = ( Builder )DynamicClassloader.newInstance( builderClazz, formalParameters,
						actualParameters );
				object = builder.create( Collections.EMPTY_MAP );
			}
			else
			{
				Map attributes = createAttributeMap( element.element( "attributes" ) );
				String clazz = element.attributeValue( "class" );
				Class[] formalParameters = new Class[] {Map.class };
				Object[] actualParameters = new Object[] {attributes };
				object = DynamicClassloader.newInstance( clazz, formalParameters, actualParameters );
			}
			
			map.put( name, object );
		}

		return map;
	}

	/**
	 * @param initializersElement
	 *            Collection of initializer desciprtion elements
	 * @return The collection of constructed initializers
	 */
	public static final List collectInitializers( Element initializersElement )
	{
		return collect( initializersElement, "initializer" );
	}

	/**
	 * @param updatesElement
	 *            Collection of update description elements
	 * @return The collection of constructed initializers
	 */
	public static final List collectUpdates( Element updatesElement )
	{
		return collect( updatesElement, "update" );
	}

	/**
	 * @param parentElement
	 *            The enclosing XML element
	 * @param targetName
	 *            The target element inside of the parenetElement
	 * @return The list of constructed target objects
	 */
	public static final List collect( Element parentElement, String targetName )
	{
		if ( parentElement == null )
		{
			return Collections.EMPTY_LIST;
		}

		List targetElements = parentElement.elements( targetName );

		int size = targetElements.size();

		if ( size == 0 )
		{
			return Collections.EMPTY_LIST;
		}

		List targets = new ArrayList( size );

		for( int x = 0; x < size; x++ )
		{
			Element targetElement = ( Element )targetElements.get( x );

			Element builderElement = targetElement.element( "builder" );

			Object target = null;

			if ( builderElement != null )
			{
				String builderClazz = builderElement.attributeValue( "class" );
				Builder builder = ( Builder )DynamicClassloader.newInstance( builderClazz,
						new Class[] {Element.class }, new Object[] {targetElement } );
				target = builder.create( Collections.EMPTY_MAP );
			}
			else
			{
				String clazz = targetElement.attributeValue( "class" );

				Map parameters = miko.xml.Util.createMap( targetElement.element( "parameters" ), "parameter", "name",
						"value" );

				target = DynamicClassloader.newInstance( clazz, new Class[] {Map.class }, new Object[] {parameters } );
			}

			targets.add( target );
		}

		return targets;
	}
}