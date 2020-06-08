/*
 * Created on Jan 30, 2004
 */
package sweep.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Element;

import standard.controller.ControllerBuilder;
import sweep.core.agent.Controller;
import automata.fsa.AutomataState;
import automata.fsa.FSA;


/**
 * @author orbital
 */
public class FSABuilder extends ControllerBuilder
{
	protected String initialStateName;
	protected Map states;

	/**
	 * @param controllerElement
	 */
	public FSABuilder( Element controllerElement )
	{
		super( controllerElement );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see standard.experiment.builder.Builder#process(org.dom4j.Element)
	 */
	protected void process( Element controllerElement )
	{
		super.process( controllerElement );

		String name = controllerElement.attributeValue( "name" );

		String format = controllerElement.attributeValue( "format" );

		states = new HashMap();

		if ( format.equals( "state" ) )
		{
			states = loadAsStates( controllerElement.element( "states" ) );
		}
		else if ( format.equals( "transition" ) )
		{
			throw new Error( "transition loading not implemented yet" );
			//states = loadAsTransitions(root.element("transitions"));
		}
		else
		{
			throw new Error( "Invalid FSA format in " + name + ": '" + format + "'" );
		}

		initialStateName = controllerElement.element( "initialState" ).attributeValue( "name" );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see standard.controller.ControllerBuilder#createController(java.util.Map)
	 */
	protected Controller createController( Map parameters )
	{
		Map newStates = new HashMap();

		Iterator i = states.keySet().iterator();

		while( i.hasNext() )
		{
			Object key = i.next();
			AutomataState value = ( AutomataState )states.get( key );
			newStates.put( key, value.copy() );
		}

		FSA fsa = new FSA( initialStateName, newStates );

		return new StateMachine( fsa );
	}

	/*
	 * private Map loadAsTransitions(Element root) { Map stateMap = new
	 * HashMap(); List transitionList = root.elements("transition"); while(
	 * transitionList.size() > 0 ) { String stateName =
	 * ((Element)transitionList.get(0)).attributeValue("state"); List list =
	 * root.selectNodes("//transitions/transition[@state='" + stateName + "']");
	 * transitionList.removeAll(list); Element stateElement =
	 * DocumentHelper.createElement("state"); stateElement.addAttribute("name",
	 * stateName); Element transitions = stateElement.addElement("transitions");
	 * transitions.elements().addAll(list); stateMap.put(stateName,
	 * StateBuilder.create(transitions.getParent()));
	 * transitionList.removeAll(list); } return stateMap; }
	 */

	private Map loadAsStates( Element root )
	{
		Map stateMap = new HashMap();
		Iterator iterator = root.elementIterator( "state" );

		while( iterator.hasNext() )
		{
			Element stateElement = ( Element )iterator.next();

			String name = stateElement.attributeValue( "name" );
			AutomataState state = AutomataStateBuilder.create( stateElement );

			stateMap.put( name, state );
		}

		return stateMap;
	}
}