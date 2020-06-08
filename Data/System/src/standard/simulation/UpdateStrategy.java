/*
 * Created on Jan 30, 2004
 */
package standard.simulation;

import java.util.Collections;
import java.util.Map;

import pattern.Strategy;
import sweep.core.agent.Agent;
import sweep.swarm.Swarm;


/**
 * @author orbital
 */
public class UpdateStrategy implements Strategy
{
	/**
	 * @param agentUpdateStrategy
	 */
	public UpdateStrategy()
	{
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see act.pattern.Command#execute(java.util.Map)
	 */
	public Map execute( Map parameters )
	{
		Agent[] agents = ( Agent[] )parameters.get( "agents" );
		Swarm[] swarms = ( Swarm[] )parameters.get( "swarms" );

		for( int x = 0; x < agents.length; x++ )
		{
			agents[x].update();
		}

		for( int x = 0; x < swarms.length; x++ )
		{
			swarms[x].update();
		}

		return Collections.EMPTY_MAP;
	}
}