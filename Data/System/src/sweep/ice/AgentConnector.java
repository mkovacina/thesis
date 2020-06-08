/*
 * Created on Mar 3, 2004
 */
package sweep.ice;

import sweep.core.agent.State;


public class AgentConnector
{
	private final AetherNet net;

	/**
	 * @param AetherNet
	 */
	public AgentConnector( AetherNet net )
	{
		this.net = net;
	}

	public void setCommands( String[] newCommands )
	{
		this.net.commands = newCommands;
	}

	public State getState()
	{
		// REFATOR: immuatable state
		return net.model.getState();
	}
}