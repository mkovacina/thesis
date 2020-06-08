package standard.simulation;

import miko.math.StaticRandom;
import sweep.core.environment.Environment;
import sweep.core.probe.Probe;
import sweep.info.Info;
import sweep.simulation.Simulation;
import sweep.swarm.Swarm;


public class DefaultSimulation implements Simulation
{
	private Swarm[] swarms;
	private Environment[] environments;
	private Probe[] probes;
	private Info info;

	/**
	 * @param swarms
	 * @param probes
	 * @param environments
	 * @param info
	 */
	public DefaultSimulation( Swarm[] swarms, Environment[] environments, Probe[] probes, Info info )
	{
		this.swarms = swarms;
		this.probes = probes;
		this.environments = environments;
		this.info = info;
	}

	public void initialize( long seed )
	{
		// REFACTOR: make sure that everyone uses this for RNG
		StaticRandom.setSeed( seed );

		for( int x = 0; x < swarms.length; x++ )
		{
			swarms[x].initialize();
		}

		for( int x = 0; x < environments.length; x++ )
		{
			environments[x].initialize();
		}

		for( int x = 0; x < probes.length; x++ )
		{
			probes[x].initialize();
		}
	}

	public void run()
	{
		boolean running = true;

		do
		{
			// the thing to do is create the avatar, which does know about both
			// the agent and the model, then just do a getAgent() kinda thing
			// on the returned avatars

			//swarm.update();
			//environment.update();

			// Step 1: update the environment
			for( int x = 0; x < environments.length; x++ )
			{
				environments[x].update();
			}

			// Step 2: update the agents' through their respective models
			// Step 3: update the swarms and agents therein
			for( int x = 0; x < swarms.length; x++ )
			{
				swarms[x].update();
			}

			// Step 4: run probes
			// This should eventually come out and be separate due to
			// EPIC/ICE
			for( int x = 0; x < probes.length; x++ )
			{
				// FIX: disabled probe updating
				running &= probes[x].update();
			}
		}
		while( running );

		for( int x = 0; x < probes.length; x++ )
		{
			// FIX: disabled probe destorying
			probes[x].destroy();
		}
	}

	public Info getInfo()
	{
		return info;
	}
}