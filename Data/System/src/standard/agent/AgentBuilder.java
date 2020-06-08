package standard.agent;

import java.util.HashMap;
import java.util.Map;

import org.dom4j.Element;

import sweep.agent.SweepAgent;
import sweep.core.agent.Controller;
import sweep.ice.AgentConnector;
import sweep.util.builder.Builder;


public class AgentBuilder extends Builder
{
	protected Map attributes;
	protected Map memory;

	public AgentBuilder( Element agentElement )
	{
		super( agentElement );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see standard.experiment.builder.Builder#initialize()
	 */
	protected void initialize()
	{
	}

	protected void process( Element agentElement )
	{
		attributes = new HashMap();
		memory = new HashMap();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see standard.experiment.builder.Builder#create(java.lang.Object[])
	 */
	public Object create( Map parameters )
	{
		Controller controller = ( Controller )parameters.get( "controller" );
		AgentConnector connector = ( AgentConnector )parameters.get( "connector" );

		return createAgent( controller, connector );
	}

	protected SweepAgent createAgent( Controller controller, AgentConnector connector )
	{
		return new SweepAgent( controller, connector, attributes, memory );
	}
}