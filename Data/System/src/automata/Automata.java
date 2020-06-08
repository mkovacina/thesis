/*
 * Created on Jan 31, 2004
 */
package automata;

import java.util.Map;


/**
 * @author orbital
 */
public interface Automata
{
	/**
	 * 
	 */
	void initialize();

	/**
	 * @param input
	 * @return Generic object to return whatever is needed
	 */
	Object evaluate( Map input );
	
	/**
	 * @return A string representatin of the current state
	 */
	String currentState();
}