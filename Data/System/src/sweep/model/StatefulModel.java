/*
 * Created on Mar 9, 2004
 */

package sweep.model;

import sweep.core.agent.State;


/**
 * @author orbital
 */
public class StatefulModel extends ModelAdapter
{
	protected State state;

	public StatefulModel()
	{
		this( new State() );
	}

	public StatefulModel( State state )
	{
		this.state = state;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hive.model.Model#getState()
	 */
	public final State getState()
	{
		// REFACTOR: should return a Read-Only copy...
		return state;
	}
}