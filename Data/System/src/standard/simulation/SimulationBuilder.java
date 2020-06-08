package standard.simulation;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Element;

import sweep.core.probe.Probe;
import sweep.environment.SweepEnvironment;
import sweep.ice.NetworkBuilder;
import sweep.info.Info;
import sweep.swarm.Swarm;
import sweep.util.builder.Builder;
import sweep.util.factory.Factory;
import sweep.util.factory.FactoryBuilder;


public class SimulationBuilder extends Builder
{

	// More cool stuff
	// there is now going to be both and agent and a model definition block
	// the way to look at it is that two or more different types of agents
	// may use the same model. so that is the razor used to separate
	// the agent and the model (hence 'internal' and 'external')

	protected Factory infoFactory;
	protected Factory agentFactory;
	protected Factory modelFactory;
	protected Factory controllerFactory;
	protected Factory environmentFactory;
	protected Factory swarmFactory;
	protected Factory probeFactory;

	protected TypeDeclaration[] infoTypes;
	protected TypeDeclaration[] swarmTypes;
	protected TypeDeclaration[] environmentTypes;
	protected TypeDeclaration[] probeTypes;

	public SimulationBuilder( Element containerElement )
	{
		super( containerElement );
	}

	public void initialize()
	{
	}

	public void process( final Element root )
	{
		infoFactory = FactoryBuilder.create( root.element( "information" ), "info" );
		agentFactory = FactoryBuilder.create( root.element( "agents" ), "agent" );
		modelFactory = FactoryBuilder.create( root.element( "models" ), "model" );
		controllerFactory = FactoryBuilder.create( root.element( "controllers" ), "controller" );
		environmentFactory = FactoryBuilder.create( root.element( "environments" ), "environment" );
		swarmFactory = FactoryBuilder.create( root.element( "swarms" ), "swarm" );
		probeFactory = FactoryBuilder.create( root.element( "probes" ), "probe" );

		Element mainElement = root.element( "main" );
		swarmTypes = extractTypes( mainElement, "swarm" );
		environmentTypes = extractTypes( mainElement, "environment" );
		probeTypes = extractTypes( mainElement, "probe" );
		infoTypes = extractTypes( mainElement, "info" );
	}

	// REFACTOR: change stuff so this can just be called parameters
	public Object create( Map inputParameters )
	{
		Map environmentMap = createEnvironments();

		NetworkBuilder networkBuilder = new NetworkBuilder( environmentMap );

		// Swarms
		Map parameters = new HashMap();

		int size = swarmTypes.length;
		Swarm[] swarms = new Swarm[size];

		parameters.put( "agentFactory", agentFactory );
		parameters.put( "controllerFactory", controllerFactory );
		parameters.put( "swarmFactory", swarmFactory );
		parameters.put( "modelFactory", modelFactory );
		parameters.put( "networkBuilder", networkBuilder );

		for( int x = 0; x < size; x++ )
		{
			parameters.put( "typeInfo", swarmTypes[x].extraParameters );
			parameters.put( "name", swarmTypes[x].name );
			swarms[x] = ( Swarm )swarmFactory.create( swarmTypes[x].type, parameters );
		}

		// Info
		// REFACTOR: just put one copy of each one (for now)
		parameters.clear();
		TypeDeclaration infoType = infoTypes[0];
		Info info = ( Info )infoFactory.create( infoType.type, parameters );

		// Probes
		size = probeTypes.length;
		Probe probes[] = new Probe[size];

		parameters.clear();
		for( int x = 0; x < size; x++ )
		{
			parameters.put( "connector", networkBuilder.createProbeConnector() );
			parameters.put( "extraParameters", probeTypes[x].extraParameters );
			probes[x] = ( Probe )probeFactory.create( probeTypes[x].type, parameters );
		}

		SweepEnvironment[] environments = ( SweepEnvironment[] )environmentMap.values().toArray(
				new SweepEnvironment[0] );

		for( int x = 0; x < environments.length; x++ )
		{
			environments[x].setConnector( networkBuilder.createEnvironmentConnector() );
		}

		return new DefaultSimulation( swarms, environments, probes, info );
	}

	/**
	 * @param parameters
	 * @return
	 */
	private Map createEnvironments()
	{
		Map parameters = Collections.EMPTY_MAP;
		Map environmentMap = new HashMap();

		for( int x = 0; x < environmentTypes.length; x++ )
		{
			SweepEnvironment environment = ( SweepEnvironment )environmentFactory.create( environmentTypes[x].type,
					parameters );
			environmentMap.put( environmentTypes[x].name, environment );
		}

		return environmentMap;
	}

	/**
	 * @param mainElement
	 * @param string
	 * @return
	 */
	private TypeDeclaration[] extractTypes( Element mainElement, String target )
	{
		List typeElements = mainElement.elements( target );
		int size = typeElements.size();
		TypeDeclaration[] types = new TypeDeclaration[size];

		for( int x = 0; x < size; x++ )
		{
			Element typeElement = ( Element )typeElements.get( x );
			String name = typeElement.attributeValue( "name" );
			String type = typeElement.attributeValue( "type" );
			List extraParameters = typeElement.elements();

			/*
			 * Map parameterMap = new HashMap();
			 * 
			 * for( int y = 0; y < extraParameters.size(); y++) { Element e =
			 * (Element)extraParameters.get(y); parameterMap.put(e.getName(),
			 * e); }
			 * 
			 * System.out.println(parameterMap.keySet()); System.exit(0);
			 */

			types[x] = new TypeDeclaration( name, type, extraParameters );
		}

		return types;
	}

	private class TypeDeclaration
	{
		final public String name;
		final public String type;
		final public List extraParameters;

		/**
		 * @param name
		 * @param type
		 * @param extraParameters
		 */
		public TypeDeclaration( final String name, final String type, final List extraParameters )
		{
			this.name = name;
			this.type = type;
			this.extraParameters = extraParameters;
		}
	}

}