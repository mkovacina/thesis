package sweep.model.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import miko.xml.Util;

import org.dom4j.Element;

import sweep.controller.SensorValue;
import sweep.controller.TestValue;
import sweep.util.builder.Builder;


/**
 * Description: ConditionalActionBuilder constructs a ConditionalAction
 * 
 * @author Michael A. Kovacina
 */
public class ConditionalActionBuilder extends Builder
{
	private String[] thenActions;
	private String[] elseActions;
	private List sensorTests;
	private List stateTests;

	/**
	 * @param containerElement
	 *            The ConditionalAction element
	 */
	public ConditionalActionBuilder( Element containerElement )
	{
		super( containerElement );
	}

	/**
	 * @see sweep.util.builder.Builder#initialize()
	 */
	protected void initialize()
	{
	}

	/**
	 * @see sweep.util.builder.Builder#process(org.dom4j.Element)
	 */
	protected void process( Element containerElement )
	{
		sensorTests = new ArrayList();
		stateTests = new ArrayList();

		Element ifElement = containerElement.element("if");
		
		List conditionalElements = ifElement.elements( "conditional" );
		for( int x = 0; x < conditionalElements.size(); x++ )
		{
			Element conditionalElement = ( Element )conditionalElements.get( x );
			String value = containerElement.attributeValue( "value" );
			String target = conditionalElement.attributeValue( "sensor", null );

			if ( target != null )
			{
				sensorTests.add( new SensorValue( target, value ) );
			}
			else
			{
				target = conditionalElement.attributeValue( "state", null );

				if ( target == null )
				{
					throw new Error( "Malformed test: " + conditionalElement.asXML() );
				}

				stateTests.add( new TestValue( target, value ) );
			}
		}

		this.thenActions = ( String[] )Util.createList( ifElement.element( "then" ), "action", "name" ).toArray(
				new String[0] );
		this.elseActions = ( String[] )Util.createList( ifElement.element( "else" ), "action", "name" ).toArray(
				new String[0] );
	}

	/**
	 * @see sweep.util.builder.Builder#create(java.util.Map)
	 */
	public Object create( Map parameters )
	{
		return new ConditionalAction( Collections.unmodifiableList( sensorTests ), Collections
				.unmodifiableList( stateTests ), thenActions, elseActions );
	}
}