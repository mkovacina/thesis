package automata.fsa;

import java.util.Iterator;
import java.util.Map;

import automata.Automata;


public class FSA implements Automata
{
	protected String initialState;
	protected AutomataState currentState;
	protected Map states;

	public FSA( String initialState, Map states )
	{
		this.initialState = initialState;
		this.states = states;
	}

	public void initialize()
	{
		currentState = ( AutomataState )states.get( initialState );
	}

	private String lastState = "---";

	public Object evaluate( Map input )
	{
		AutomataState tempCurrentState = currentState;
		String nextState = currentState.nextState( input );

		if ( nextState == null )
		{
			System.err.print( "[0] '" + lastState );
			System.err.print( "'  =>  [1] '" + currentState.getName() );
			System.err.println( "'  =>  [2] '" + nextState + "'" );
			throw new Error( "NextState is null..." );
		}

		Object output = currentState.getOutput();
		lastState = currentState.name;
		currentState = ( AutomataState )states.get( nextState );

		if ( currentState == null )
		{
			throw new Error( "Transition to non-existant state: " + tempCurrentState.name + " => " + nextState );
		}

		return output;
	}

	public String toString()
	{
		StringBuffer buffer = new StringBuffer( 255 );

		buffer.append( "**| Controller Prototype: " );
		buffer.append( this.getClass().getName() );
		buffer.append( '\n' );

		buffer.append( "**| + current state: " );
		buffer.append( currentState );
		buffer.append( '\n' );

		buffer.append( "**| + states: \n" );
		Iterator keys = states.keySet().iterator();
		while( keys.hasNext() )
		{
			Object key = keys.next();
			buffer.append( "**|    * " );
			buffer.append( key );
			buffer.append( '\n' );
		}

		return buffer.toString();
	}

	/** 
	 * @see automata.Automata#currentState()
	 */
	public String currentState()
	{
		return this.currentState.name;
	}
}