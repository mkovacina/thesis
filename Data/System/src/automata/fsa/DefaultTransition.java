package automata.fsa;

import rule.Rule;


public class DefaultTransition extends Transition
{
	public DefaultTransition( String[] actions, String nextState )
	{
		super( new Rule[0], actions, nextState );
	}

	public Transition copy()
	{
		String[] newActions = new String[actions.length];

		// TODO: is this correct??
		System.arraycopy( actions, 0, newActions, 0, actions.length );

		return new DefaultTransition( newActions, nextState );
	}
}