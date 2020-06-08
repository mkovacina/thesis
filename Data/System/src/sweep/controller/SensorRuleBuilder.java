package sweep.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Element;

import rule.Rule;
import rule.RuleBuilder;


/**
 * Description: SensorRuleBuilder constructs a rule that uses sensor and attribute information.
 * 
 * @author Michael A. Kovacina
 */
public class SensorRuleBuilder implements RuleBuilder
{
	/** 
	 * @see rule.RuleBuilder#create(org.dom4j.Element)
	 */
	public Rule create( Element root )
	{
		Map sensors = new HashMap();
		Iterator iter = root.elementIterator( "sensor" );
		String logical = root.attributeValue( "logical", "" );

		while( iter.hasNext() )
		{
			Element sensor = ( Element )iter.next();

			sensors.put( sensor.attributeValue( "name" ), sensor.attributeValue( "value" ) );
		}

		return new SensorRule( sensors, logical );
	}
}