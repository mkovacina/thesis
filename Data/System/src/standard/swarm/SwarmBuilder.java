/*
 * Created on Jan 30, 2004
 */

package standard.swarm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import miko.xml.Util;

import org.dom4j.Element;

import sweep.agent.SweepAgent;
import sweep.avatar.SweepAvatar;
import sweep.core.agent.Controller;
import sweep.ice.AetherNet;
import sweep.ice.AgentConnector;
import sweep.ice.AvatarConnector;
import sweep.ice.ModelConnector;
import sweep.ice.NetworkBuilder;
import sweep.model.AvatarModel;
import sweep.swarm.Entity;
import sweep.swarm.Swarm;
import sweep.util.builder.Builder;
import sweep.util.factory.Factory;


/**
 * @author orbital
 */
public class SwarmBuilder extends Builder
{
	// REFACTOR: consider using specific factories instead of generic...

	protected Map entityTypes;

	/**
	 * @param swarmElement
	 */
	public SwarmBuilder( Element swarmElement )
	{
		super( swarmElement );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see standard.experiment.builder.Builder#initialize()
	 */
	protected void initialize()
	{
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see standard.experiment.builder.Builder#process(org.dom4j.Element)
	 */
	protected void process( Element swarmElement )
	{
		List entityElements = swarmElement.elements( "entity" );
		int size = entityElements.size();
		entityTypes = new HashMap();

		for( int x = 0; x < size; x++ )
		{
			Element entityElement = ( Element )entityElements.get( x );

			String name = entityElement.attributeValue( "name" );
			String agentType = entityElement.element( "agent" ).attributeValue( "type" );
			String controllerType = entityElement.element( "controller" ).attributeValue( "type" );
			String modelType = entityElement.element( "model" ).attributeValue( "type" );

			entityTypes.put( name, new EntityHolder( name, agentType, controllerType, modelType ) );
		}
	}

	/** 
	 * @see sweep.util.builder.Builder#create(java.util.Map)
	 */
	public Object create( Map parameters )
	{
		Factory agentFactory = ( Factory )parameters.get( "agentFactory" );
		Factory controllerFactory = ( Factory )parameters.get( "controllerFactory" );
		NetworkBuilder networkBuilder = ( NetworkBuilder )parameters.get( "networkBuilder" );
		Factory modelFactory = ( Factory )parameters.get( "modelFactory" );
		List typeInfo = ( List )parameters.get( "typeInfo" );
		String name = (String)parameters.get("name");

		List entityTypeList = new ArrayList();

		for( int x = 0; x < typeInfo.size(); x++ )
		{
			Element entityElement = ( Element )typeInfo.get( x );
			EntityType type = new EntityType();
			type.swarmName = name;

			type.name = entityElement.attributeValue( "type" );

			EntityHolder holder = ( EntityHolder )entityTypes.get( type.name );

			type.agent = holder.agent;
			type.model = holder.model;
			type.controller = holder.controller;
			type.number = Integer.parseInt( entityElement.attributeValue( "number" ) );

			Element modelElement = entityElement.element( "model" );
			type.modelAttributes = Util.createMap( modelElement, "attribute", "name", "value" );

			entityTypeList.add( type );
		}

		return createSwarm( entityTypeList, agentFactory, controllerFactory, modelFactory, networkBuilder );
	}

	protected Swarm createSwarm( List typeList, Factory agentFactory, Factory controllerFactory, Factory modelFactory,
			NetworkBuilder networkBuilder )
	{
		Iterator typeIterator = typeList.iterator();

		List entityList = new ArrayList();

		while( typeIterator.hasNext() )
		{
			EntityType type = ( EntityType )typeIterator.next();
			List temp = assembleEntities( type, agentFactory, controllerFactory, modelFactory, networkBuilder );
			entityList.addAll( temp );
		}

		Entity[] entities = ( Entity[] )entityList.toArray( new Entity[0] );

		return new Swarm( entities );
	}

	protected List assembleEntities( EntityType type, Factory agentFactory, Factory controllerFactory,
			Factory modelFactory, NetworkBuilder networkBuilder )
	{
		List entities = new ArrayList( type.number );
		Map specificParameters = new HashMap();

		for( int x = 0; x < type.number; x++ )
		{
			specificParameters.clear();

			Controller controller = ( Controller )controllerFactory.create( type.controller, specificParameters );

			AetherNet aethernet = networkBuilder.createNetwork();
			AgentConnector agentConnector = aethernet.getAgentConnector();
			AvatarConnector avatarConnector = aethernet.getAvatarConnector();
			ModelConnector modelConnector = aethernet.getModelConnector();

			specificParameters.put( "controller", controller );
			specificParameters.put( "connector", agentConnector );

			SweepAgent agent = ( SweepAgent )agentFactory.create( type.agent, specificParameters );

			specificParameters.clear();
			specificParameters.put( "connector", modelConnector );
			specificParameters.put( "attributes", type.modelAttributes );

			AvatarModel model = ( AvatarModel )modelFactory.create( type.model, specificParameters );

			SweepAvatar avatar = new SweepAvatar( model, avatarConnector );
			// XXX: heaven help me, is this a hack, or smart?
			model.getState().put("swarm", type.swarmName);
			
			// REFACTOR: don't like it, but it's a chicken-and-egg problem
			aethernet.setAgent( agent );
			aethernet.setAvatar( avatar );
			aethernet.setModel( model );

			Entity entity = new Entity( agent, avatar );

			entities.add( entity );
		}

		return entities;
	}

	protected class EntityHolder
	{
		final public String name;
		final public String agent;
		final public String controller;
		final public String model;

		public EntityHolder( String name, String agent, String controller, String model )
		{
			this.name = name;
			this.agent = agent;
			this.controller = controller;
			this.model = model;
		}

		public String toString()
		{
			return "<" + name + ", " + agent + ", " + controller + ", " + model + ">";
		}
	}

	private class EntityType
	{
		// REFACTOR: make these final and put in a constructor
		public String name;
		public int number;
		public Map modelAttributes;
		public String agent;
		public String controller;
		public String model;
		public String swarmName;
	}
}