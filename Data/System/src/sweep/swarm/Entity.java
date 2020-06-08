/*
 * Created on Mar 10, 2004
 */

package sweep.swarm;

import sweep.agent.SweepAgent;
import sweep.avatar.SweepAvatar;


/**
 * @author orbital
 */
public class Entity
{
	final protected SweepAgent agent;
	final protected SweepAvatar avatar;

	/**
	 *  
	 */
	public Entity( SweepAgent agent, SweepAvatar avatar )
	{
		this.agent = agent;
		this.avatar = avatar;
	}

	public void initialize()
	{
		avatar.initialize();
		agent.initialize();
	}

	public void update()
	{
		// (1) [avatar] update the model's sensor information
		// (2) [agent] compute the next action
		// (3) [avatar] execute the computed action

		avatar.updateSensors();
		agent.update();
		avatar.executeNextAction();

		//avatar.update();
		//agent.update();
	}

	public void destroy()
	{
		avatar.destroy();
		agent.destroy();
	}
}