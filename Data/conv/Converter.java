import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import miko.xml.Util;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;


/**
 * Description: <code>Converter</code>
 * 
 * @author Michael A. Kovacina
 */
public class Converter
{
	public static void main( String args[] ) throws FileNotFoundException, DocumentException
	{
		Document doc = Util.createDocumentFromFile( args[0] );

		Element root = doc.getRootElement();
		List states = root.element( "states" ).elements( "state" );

		Map stateNames = new HashMap();
		char c = 'A';
		
		for( int i = 0; i < states.size(); i++ )
		{
			Element state = ( Element )states.get( i );
			String stateName = state.attributeValue( "name" );

			if ( stateNames.containsKey( stateName ) == false )
			{
				stateNames.put( stateName, "" + c );
				c++;
			}
		}		

		for( int i = 0; i < states.size(); i++ )
		{
			Element state = ( Element )states.get( i );
			String stateName = state.attributeValue( "name" );
			List transitions = state.elements( "transition" );

			Set sensorConditions = new HashSet();
			String sep = " & ";


			// sure, i can do better string writing wise, but not now
			String text = "";
			int numTransitions=0;

			for( int j = 0; j < transitions.size(); j++ )
			    {
				Element transition = ( Element )transitions.get( j );
				List sensors = transition.element("rule").elements("sensor");

				numTransitions++;
				String sensorText = "";

				for( int z = 0; z < sensors.size(); z++ )
				    {
					//Element sensor = transition.element( "rule" ).element( "sensor" );
					Element sensor = (Element)sensors.get(z);

					String sensorName = sensor.attributeValue( "name" );
					String sensorValue = sensor.attributeValue( "value" );
					String sensorFlag = sensorValue.equals("false") ? "\\parbox{1ex}{!}" : "\\parbox{1ex}{~}";
					String key = sensorName + ":" + sensorValue;
				
					//if ( sensorConditions.contains( key ) )
					//{
					//continue;
					//}
				
					//sensorConditions.add( key );
				
					//text = text + sep + sensorFlag + sensorName + sep + actionName + sep + nextState + "\\\\\n";
					if ( z == sensors.size() - 1 )
					    {
						sensorText += sensorFlag + sensorName;
					    }
					else
					    {
						sensorText += sensorFlag + sensorName+", ";
					    }
					//if ( sensorConditions.contains(sensorName+":true")
					//     && sensorConditions.contains(sensorName+":false"))
					//    {
					//	break;
					//   }
				    }
				String actionName = transition.element("action").attributeValue("name");
				String nextState = (String)stateNames.get(transition.attributeValue( "nextState" ));

				text = text + sep + sensorText + sep + actionName + sep + nextState + "\\\\\n";
			    }

			String defaultState = state.element("default").attributeValue("nextState");
			String defaultAction = state.element("default").element("action").attributeValue("name");

			text = text + sep + "default" + sep + defaultAction + sep  + (String)stateNames.get(defaultState)+ "\\\\\n";

			numTransitions++;
			
			System.out.println( "\\multirow{"+numTransitions+"}{*}{"+stateNames.get(stateName)+"}");
			System.out.print(text);
			System.out.println("\\hline");
			System.out.println();
		}
	}
}
