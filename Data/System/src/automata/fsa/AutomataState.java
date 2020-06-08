package automata.fsa;

import java.util.Map;


public class AutomataState
{
	protected String name;
	protected Transition[] transitions;
	protected Transition defaultTransition;

	protected Transition currentTransition;

	public AutomataState( final String name, final Transition[] transitions, final Transition defaultTransition )
	{
		// XXX: should assert( name != null )
		this.name = name;
		this.transitions = transitions;

		this.defaultTransition = defaultTransition;

		this.currentTransition = null;
	}

	public String nextState( Map input )
	{
		currentTransition = null;

		for( int x = 0; x < transitions.length; x++ )
		{
			if ( transitions[x].isTrue( input ) )
			{
				currentTransition = transitions[x];
				break;
			}
		}

		if ( currentTransition == null )
		{
			currentTransition = defaultTransition;
		}

		return currentTransition.nextState();
	}

	public Object getOutput()
	{
		return currentTransition.invoke();
	}

	public String getName()
	{
		return name;
	}

	public AutomataState copy()
	{
		return new AutomataState( name, transitions, defaultTransition );
	}
}