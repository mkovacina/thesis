/*
 * Created on Mar 3, 2004
 */
package sweep.ice;

import java.util.Map;


public class ModelConnector
{
	private final AetherNet net;

	/**
	 * @param AetherNet
	 */
	public ModelConnector( AetherNet net )
	{
		this.net = net;
	}

	public String[] getCommands()
	{
		if ( net.commands == null )
		{
			return new String[0];
		}

		// REFACTOR: immuatable commands
		return this.net.commands;
	}

	public Map getEnvironmentMap()
	{
		return net.environmentMap;
	}
}