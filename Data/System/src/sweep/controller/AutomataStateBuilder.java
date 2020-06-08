package sweep.controller;

import java.util.List;
import java.util.ListIterator;

import miko.loader.DynamicClassloader;

import org.dom4j.Element;

import rule.Rule;
import rule.RuleBuilder;
import automata.fsa.AutomataState;
import automata.fsa.DefaultTransition;
import automata.fsa.Transition;


public class AutomataStateBuilder
{
	protected AutomataStateBuilder()
	{
	}

	public static AutomataState create( Element root )
	{
		String stateName = root.attributeValue( "name" );

		List transitionList = root.elements( "transition" );
		int size = root.elements( "transition" ).size();

		Transition[] transitions = new Transition[size];

		for( int x = 0; x < size; x++ )
		{
			Element transitionElement = ( Element )transitionList.get( x );

			List ruleElementList = transitionElement.elements( "rule" );
			Rule[] rules = createRules( ruleElementList );

			List actionElementList = transitionElement.elements( "action" );
			String[] actions = createActions( actionElementList );

			String nextState = transitionElement.attributeValue( "nextState" );

			transitions[x] = new Transition( rules, actions, nextState );
		}

		Element defaultTransitionElement = root.element( "default" );
		Transition defaultTransition = null;

		if ( defaultTransitionElement != null )
		{
			// actions
			List actionElementList = defaultTransitionElement.elements( "action" );
			String[] actions = createActions( actionElementList );

			String nextState = defaultTransitionElement.attributeValue( "nextState" );
			defaultTransition = new DefaultTransition( actions, nextState );
		}
		else
		{
			// If no default, then loop back to self
			defaultTransition = new DefaultTransition( new String[0], stateName );
		}

		return new AutomataState( stateName, transitions, defaultTransition );
	}

	private static Rule[] createRules( List ruleElementList )
	{
		int numRules = ruleElementList.size();
		Rule[] rules = new Rule[numRules];

		ListIterator iterator = ruleElementList.listIterator();

		for( int x = 0; iterator.hasNext(); x = iterator.nextIndex() )
		{
			Element ruleElement = ( Element )iterator.next();

			String classname = ruleElement.element( "builder" ).attributeValue( "class" );
			RuleBuilder builder = ( RuleBuilder )DynamicClassloader.newInstance( classname );

			rules[x] = builder.create( ruleElement );
		}

		return rules;
	}

	private static String[] createActions( List actionElementList )
	{
		int numActions = actionElementList.size();
		String[] actions = new String[numActions];

		for( int x = 0; x < numActions; x++ )
		{
			actions[x] = ( ( Element )actionElementList.get( x ) ).attributeValue( "name" );
		}

		return actions;
	}
}