package sweep.ice;

import java.util.Map;

import sweep.agent.SweepAgent;
import sweep.avatar.SweepAvatar;
import sweep.core.agent.State;
import sweep.model.AvatarModel;


/**
 * @author orbital
 */
public class AetherNet
{
	protected SweepAgent agent;
	protected AgentConnector agentConnector;

	protected SweepAvatar avatar;
	protected AvatarConnector avatarConnector;

	protected AvatarModel model;
	protected ModelConnector modelConnector;

	protected String[] commands;
	protected State state;

	final protected Map environmentMap;

	protected AetherNet( Map environmentMap )
	{
		this.environmentMap = environmentMap;
	}

	public AgentConnector getAgentConnector()
	{
		// REFACTOR: how to handle if called twice
		this.agentConnector = new AgentConnector( this );

		return agentConnector;
	}

	public void setAgent( SweepAgent agent )
	{
		this.agent = agent;
	}

	public AvatarConnector getAvatarConnector()
	{
		// REFACTOR: how to handle if called twice
		this.avatarConnector = new AvatarConnector( this );

		return avatarConnector;
	}

	public void setAvatar( SweepAvatar avatar )
	{
		this.avatar = avatar;
	}

	public ModelConnector getModelConnector()
	{
		this.modelConnector = new ModelConnector( this );

		return modelConnector;
	}

	public void setModel( AvatarModel model )
	{
		this.model = model;
	}
}