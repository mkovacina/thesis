/*
 * Created on Jan 30, 2004
 */
package sweep.util.factory;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import miko.loader.DynamicClassloader;

import org.dom4j.Element;

import sweep.util.builder.Builder;


/**
 * @author orbital
 */
public class FactoryBuilder
{
	private FactoryBuilder()
	{
	}

	public static Factory create( final Element root, String iteratorTarget )
	{
		Iterator iterator = root.elementIterator( iteratorTarget );
		Map map = new HashMap();

		while( iterator.hasNext() )
		{
			Element element = ( Element )iterator.next();

			String name = element.attributeValue( "name" );
			String builderClass = element.element( "builder" ).attributeValue( "class" );

			// Step 1: load the builder class
			Class[] formalParameters = new Class[] {Element.class };
			Object[] actualParameters = new Object[] {element };
			Builder builder = ( Builder )DynamicClassloader.newInstance( builderClass, formalParameters,
					actualParameters );

			// Step 2: create the prototype from the builder
			//Object prototype = builder.create(element);

			//System.out.println(prototype);

			// Step 3: add the prototype to the list
			map.put( name, builder );
		}

		return new Factory( map );
	}
}