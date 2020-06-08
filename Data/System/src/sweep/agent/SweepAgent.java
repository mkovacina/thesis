package sweep.agent;

import java.util.Map;

import sweep.core.agent.Agent;
import sweep.core.agent.Controller;
import sweep.core.agent.State;
import sweep.ice.AgentConnector;


public class SweepAgent implements Agent
{
	protected Controller controller;
	protected AgentConnector connector;
	protected Map attributes;
	protected Map memory;
	protected State state;

	protected String[] actions;

	/**
	 * @param controller
	 * @param model
	 * @param attributes
	 * @param memory
	 */
	public SweepAgent( final Controller controller, final AgentConnector connector, final Map attributes,
			final Map memory )
	{
		this.controller = controller;
		this.connector = connector;
		this.attributes = attributes;
		this.memory = memory;
	}

	public void initialize()
	{
		controller.initialize();

		state = connector.getState();
		state.put( "memory", memory );
	}

	public void update()
	{
		state = connector.getState();
		actions = controller.update( state );

		// REFACTOR: should this information be "pushed" or "pulled"
		connector.setCommands( actions );
	}

	public void destroy()
	{
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see miko.query.Queryable#query(java.lang.String)
	 */
	public Object query( String queryString )
	{
		// REFACTOR: pass the agent constructor a Queryable object that has all
		// the query actions loaded from the file
		return null;
	}
}