/*
 * Created on Jan 30, 2004
 */
package sweep.info;

import java.util.List;
import java.util.Map;

import org.dom4j.Element;

import standard.info.InfoBuilder;


/**
 * @author orbital
 */
public class DevInfoBuilder extends InfoBuilder
{
	private String[] authors;
	private String project;
	private String description;

	/**
	 * @param containerElement
	 */
	public DevInfoBuilder( Element infoElement )
	{
		super( infoElement );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see standard.experiment.builder.Builder#process(org.dom4j.Element)
	 */
	protected void process( Element infoElement )
	{
		// TEST: verify all information returned

		// BUG: eclipse
		// String authors[] = getAuthors(infoElement.element("authors"));
		// Apply Refactor->ConverLocalVariableToField...
		// Does not respect the []

		authors = getAuthors( infoElement.element( "authors" ) );
		project = infoElement.element( "project" ).attributeValue( "name" );
		description = infoElement.element( "description" ).getTextTrim();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see standard.info.InfoBuilder#createInfo(java.util.Map)
	 */
	protected Info createInfo( Map parameters )
	{
		return new DevInfo( authors, project, description );
	}

	private static String[] getAuthors( Element root )
	{
		List authorElements = root.elements( "author" );
		int size = authorElements.size();

		String[] authors = new String[size];

		for( int x = 0; x < size; x++ )
		{
			Element authorElement = ( Element )authorElements.remove( 0 );
			authors[x] = authorElement.attributeValue( "name" );
		}

		return authors;
	}
}