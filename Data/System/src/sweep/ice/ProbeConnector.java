/*
 * Created on Mar 3, 2004
 */
package sweep.ice;

import java.util.List;
import java.util.Map;


public class ProbeConnector
{
	// REFACTOR: will size(agents) == size(avatars) == size(models) always
	public Map environmentMap;
	public List agents;
	public List avatars;
	public List models;

	/**
	 * @param environmentMap
	 * @param agents
	 * @param avatars
	 * @param models
	 */
	public ProbeConnector( Map environmentMap, List agents, List avatars, List models )
	{
		this.environmentMap = environmentMap;
		this.agents = agents;
		this.avatars = avatars;
		this.models = models;
	}
}