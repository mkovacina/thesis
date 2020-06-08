package miko.xml;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import miko.collection.Arrays;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;


public final class Util
{
	private Util()
	{
	}

	public static final Document createDocumentFromFile( String filename ) throws FileNotFoundException,
			DocumentException
	{
		SAXReader reader = new SAXReader();

		Document document = null;

		document = reader.read( new FileReader( filename ) );

		return document;
	}

	public static final Map createMap( Element containerElement, String childName, String nameStr, String valueStr )
	{
		Map map = new HashMap();

		if ( containerElement == null )
		{
			return Collections.EMPTY_MAP;
		}

		List childElements = containerElement.elements( childName );

		if ( childElements.size() == 0 )
		{
			return Collections.EMPTY_MAP;
		}

		Iterator childElementIterator = childElements.iterator();

		while( childElementIterator.hasNext() )
		{
			Element childElement = ( Element )childElementIterator.next();

			String name = childElement.attributeValue( nameStr );
			String value = childElement.attributeValue( valueStr );

			map.put( name, value );
		}

		return map;
	}

	public static final List createList( Element containerElement, String childName, String attributeName )
	{
		if ( containerElement == null )
			return Collections.EMPTY_LIST;

		List childElementList = containerElement.elements( childName );
		int size = childElementList.size();

		List list = new ArrayList( size );

		for( int x = 0; x < size; x++ )
		{
			Element childElement = ( Element )childElementList.get( x );

			String value = childElement.attributeValue( attributeName );

			list.add( value );
		}

		return list;
	}

	public static final void prettyPrintDocument( PrintStream stream, Document doc )
	{
		try
		{
			OutputFormat format = OutputFormat.createPrettyPrint();
			XMLWriter writer = new XMLWriter( new OutputStreamWriter( stream ), format );
			writer.write( doc );
			writer.close();
		}
		catch( Exception e )
		{
		}
	}

	public static Document selectAndReplace( Document doc, String xpath, String attributeName, String[] alternatives )
	{
		// XXX: might not belong here, but in SweepMutation...
		List actionElements = doc.selectNodes( xpath );
		Element actionElement = ( Element )miko.collection.Collections.randomSelection( actionElements );
		actionElement.addAttribute( attributeName, ( String )Arrays.randomSelection( alternatives ) );

		return doc;
	}
}