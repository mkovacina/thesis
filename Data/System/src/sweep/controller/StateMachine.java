package sweep.controller;

import java.util.HashMap;
import java.util.Map;

import sweep.core.agent.Controller;
import sweep.core.agent.State;
import automata.Automata;


/**
 * @author orbital
 */
public class StateMachine implements Controller
{
	final private Automata automata;
	private Map input;
	private final static String[] NO_ACTIONS = new String[0];

	/**
	 * @param automata
	 *  
	 */
	public StateMachine( final Automata automata )
	{
		this.automata = automata;
	}

	/** 
	 * @see sweep.core.agent.Controller#initialize()
	 */
	public void initialize()
	{
		input = new HashMap();
		automata.initialize();
	}

	/** 
	 * @see sweep.core.agent.Controller#update(sweep.core.agent.State)
	 */
	public String[] update( State state )
	{
		if ( state.get( "active" ).equals( Boolean.FALSE ) )
			return NO_ACTIONS;
		
		input.put( "state", state );
		// XXX: System.out.println(automata.currentState());
		return ( String[] )automata.evaluate( input );
	}
}