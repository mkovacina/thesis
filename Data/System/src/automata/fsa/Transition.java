package automata.fsa;

import java.util.Map;

import rule.Rule;


// REFACTOR: make Transition an interface, then can be extended to simple,
// complex, fuzzy,...

public class Transition
{
	protected Rule[] rules;
	protected String[] actions;
	protected final String nextState;

	public Transition( final Rule[] rules, final String[] actions, final String nextState )
	{
		if ( nextState == null )
		{
			throw new NullPointerException( "WTF!!!!" );
		}

		this.rules = rules;
		this.actions = actions;
		this.nextState = nextState;
	}

	public boolean isTrue( Map input )
	{
		boolean OK = true;

		for( int x = 0; OK && x < rules.length; x++ )
		{
			OK &= rules[x].eval( input );
		}

		return OK;
	}

	public String nextState()
	{
		return nextState;
	}

	public String[] invoke()
	{
		return actions;
	}

	public Transition copy()
	{
		String[] newActions = new String[actions.length];

		for( int x = 0; x < actions.length; x++ )
		{
			newActions[x] = actions[x].toString();
		}

		Rule[] newRules = new Rule[rules.length];

		for( int x = 0; x < rules.length; x++ )
		{
			newRules[x] = rules[x].copy();
		}
		return new Transition( newRules, newActions, nextState );
	}
}